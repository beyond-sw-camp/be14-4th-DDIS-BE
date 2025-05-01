package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.ApproveResponseDTO;
import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.approve.Command.domain.aggregate.Entity.MemberApprove;
import com.DDIS.approve.Command.domain.repository.ApproveRepository;
import com.DDIS.approve.Command.domain.repository.MemberApproveRepository;
import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.*;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApproveServiceImpl implements ApproveService {
    private final ApproveRepository approveRepository;
    private final MemberRepository memberRepository;
    private final MemberApproveRepository memberApproveRepository;
    private final MemberShareTodoRepository memberShareTodoRepository;
    private final MemberShareTodoDateRepository memberShareTodoDateRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ApproveServiceImpl(ApproveRepository approveRepository,
                              MemberRepository memberRepository,
                              MemberApproveRepository memberApproveRepository,
                              MemberShareTodoRepository memberShareTodoRepository,
                              MemberShareTodoDateRepository memberShareTodoDateRepository,
                              RoomRepository roomRepository) {
        this.approveRepository = approveRepository;
        this.memberRepository = memberRepository;
        this.memberApproveRepository = memberApproveRepository;
        this.memberShareTodoRepository = memberShareTodoRepository;
        this.memberShareTodoDateRepository = memberShareTodoDateRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Long createApprove(CreateApproveDTO approveDTO) {
        Members member = memberRepository.findById(approveDTO.getMemberNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 없음"));

        MemberShareTodo memberShareTodo = memberShareTodoRepository.findById(approveDTO.getMemberShareTodoNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버공동투두 없음"));

        MemberShareTodoDateId id = new MemberShareTodoDateId(
                approveDTO.getTodoDate(),
                approveDTO.getMemberShareTodoNum()
        );

        MemberShareTodoDate todoDateEntity = memberShareTodoDateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 TODO 없음"));

        boolean exists = approveRepository.existsByMemberShareTodoNumAndMemberShareTodoDate(
                memberShareTodo, todoDateEntity
        );

        if (exists) {
            throw new RuntimeException("해당 TODO 날짜에 이미 승인 요청이 존재합니다.");
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Approve approve = Approve.builder()
                .roomNum(approveDTO.getRoomNum())
                .memberNum(member)
                .memberShareTodoNum(memberShareTodo)
                .approveTitle(approveDTO.getApproveTitle())
                .approveContent(approveDTO.getApproveContent())
                .approveTime(now)
                .approvePermitCount(0)
                .approveRefuseCount(0)
                .todoDate(approveDTO.getTodoDate())
                .build();

        Approve saved = approveRepository.save(approve);
        return saved.getApproveNum();
    }

    @Override
    public void updateApproveStatus(UpdateApproveStatusDTO dto) {
        Approve approve = approveRepository.findById(dto.getApproveNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 approve가 존재하지 않습니다."));

        Members member = memberRepository.findById(dto.getMemberNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        boolean alreadyApproved = memberApproveRepository
                .existsByApprove_ApproveNumAndMember_MemberNum(dto.getApproveNum(), dto.getMemberNum());

        if (alreadyApproved) {
            throw new IllegalStateException("이미 승인 또는 거절한 사용자입니다.");
        }

        switch (dto.getAction()) {
            case "permit" -> approve.setApprovePermitCount(approve.getApprovePermitCount() + 1);
            case "refuse" -> approve.setApproveRefuseCount(approve.getApproveRefuseCount() + 1);
            default -> throw new IllegalArgumentException("허용되지 않은 액션입니다.");
        }

        MemberApprove record = MemberApprove.builder()
                .approve(approve)
                .member(member)
                .approveStatus("permit".equals(dto.getAction()))
                .build();

        memberApproveRepository.save(record);
        approveRepository.save(approve);

        if ("permit".equals(dto.getAction())) {
            MemberShareTodo memberShareTodo = approve.getMemberShareTodoNum();

            // 방 정보 조회
            Rooms room = roomRepository.findById(approve.getRoomNum())
                    .orElseThrow(() -> new IllegalArgumentException("해당 방을 찾을 수 없습니다."));

            // 승인 수 기준 이상일 때 처리
            if (approve.getApprovePermitCount() >= room.getApproveRequiredCount()) {
                // 날짜 추출 (approveTime이 "2025-04-30 11:34:57" 같은 형식일 경우)
                String dateStr = approve.getTodoDate();

                // memberShareTodoDate 조회
                MemberShareTodoDate dateEntity = memberShareTodoDateRepository
                        .findByTodoDateAndMemberShareTodoNum(dateStr, memberShareTodo.getMemberShareTodoNum())
                        .orElseThrow(() -> new IllegalArgumentException("일치하는 날짜 데이터가 없습니다."));

                dateEntity.setDone(true);
                memberShareTodoDateRepository.save(dateEntity);
            }
        }

    }

    @Override
    public List<ApproveResponseDTO> getAllApproves() {
        List<Approve> approves = approveRepository.findAll();
        return approves.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApproveResponseDTO getApprove(Long id) {
        Approve approve = approveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 승인 요청이 존재하지 않습니다. id=" + id));
        return toDTO(approve);
    }

    private ApproveResponseDTO toDTO(Approve approve) {
        return ApproveResponseDTO.builder()
                .approveId(approve.getApproveNum())
                .memberShareTodoNum(approve.getMemberShareTodoNum().getMemberShareTodoNum())
                .memberNum(approve.getMemberNum().getMemberNum())
                .approveTitle(approve.getApproveTitle())
                .approveContent(approve.getApproveContent())
                .approveTime(approve.getApproveTime())
                .build();
    }

    @Override
    public List<ApproveResponseDTO> getUnvotedApproves(Long memberNum, Long roomNum) {
        List<Approve> approves = approveRepository.findUnvotedApprovesByMember(memberNum, roomNum);
        return approves.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

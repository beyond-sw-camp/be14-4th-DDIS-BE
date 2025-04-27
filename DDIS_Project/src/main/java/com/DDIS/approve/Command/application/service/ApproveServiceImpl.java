package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.ApproveResponseDTO;
import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.approve.Command.domain.repository.ApproveRepository;
import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
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
    private final MemberShareTodoRepository memberShareTodoRepository;

    @Autowired
    public ApproveServiceImpl(ApproveRepository approveRepository, MemberRepository memberRepository, MemberShareTodoRepository memberShareTodoRepository) {
        this.approveRepository = approveRepository;
        this.memberRepository = memberRepository;
        this.memberShareTodoRepository = memberShareTodoRepository;
    }


    @Override
    @Transactional
    public Long createApprove(CreateApproveDTO approveDTO) {
        Members member = memberRepository.findById(approveDTO.getMemberNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 없음"));

        MemberShareTodo memberShareTodo = memberShareTodoRepository.findById(approveDTO.getMemberShareTodoNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버공동투두 없음"));

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Approve approve = Approve.builder()
                .memberNum(member)
                .memberShareTodoNum(memberShareTodo)
                .approveTitle(approveDTO.getApproveTitle())
                .approveContent(approveDTO.getApproveContent())
                .approveTime(now) // LocalDateTime이면 그대로
                .approvePermitCount(0)
                .approveRefuseCount(0)
                .build();

        Approve saved = approveRepository.save(approve);
        return saved.getApproveNum();

    }

    @Override
    @Transactional
    public void updateApproveStatus(UpdateApproveStatusDTO dto) {
        Approve approve = approveRepository.findById(dto.getApproveNum())
                .orElseThrow(() -> new IllegalArgumentException("해당 approve가 존재하지 않습니다."));

        switch (dto.getAction()) {
            case "permit" -> approve.setApprovePermitCount(approve.getApprovePermitCount() + 1);
            case "refuse" -> approve.setApproveRefuseCount(approve.getApproveRefuseCount() + 1);
            default -> throw new IllegalArgumentException("허용되지 않은 액션입니다.");
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
}

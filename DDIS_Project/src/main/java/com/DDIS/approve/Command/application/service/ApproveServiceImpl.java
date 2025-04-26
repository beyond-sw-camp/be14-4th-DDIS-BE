//package com.DDIS.approve.Command.application.service;
//
//import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
//import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
//import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
//import com.DDIS.approve.Command.domain.repository.ApproveRepository;
//import com.DDIS.approve.Command.domain.repository.MemberRepository;
//import com.DDIS.approve.Command.domain.repository.MemberShareTodoRepository;
//import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
//import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class ApproveServiceImpl implements ApproveService {
//    private final ApproveRepository approveRepository;
//    private final MemberRepository memberRepository;
//    private final MemberShareTodoRepository memberShareTodoRepository;
//
//    @Autowired
//    public ApproveServiceImpl(ApproveRepository approveRepository, MemberRepository memberRepository, MemberShareTodoRepository memberShareTodoRepository) {
//        this.approveRepository = approveRepository;
//        this.memberRepository = memberRepository;
//        this.memberShareTodoRepository = memberShareTodoRepository;
//    }
//
//
//    @Override
//    @Transactional
//    public Long createApprove(CreateApproveDTO approveDTO) {
//        Members member = memberRepository.findById(approveDTO.getMemberNum())
//                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 없음"));
//
//        MemberShareTodo memberShareTodo = memberShareTodoRepository.findById(approveDTO.getMemberShareTodoNum())
//                .orElseThrow(() -> new IllegalArgumentException("해당 멤버공동투두 없음"));
//
//        Approve approve = Approve.builder()
//                .memberNum(member)
//                .memberShareTodoNum(memberShareTodo)
//                .approveTitle(approveDTO.getApproveTitle())
//                .approveContent(approveDTO.getApproveContent())
//                .approveTime(approveDTO.getApproveTime()) // LocalDateTime이면 그대로
//                .approvePermitCount(0)
//                .approveRefuseCount(0)
//                .build();
//
//        Approve saved = approveRepository.save(approve);
//        return saved.getApproveNum();
//
//    }
//
//    @Override
//    public void updateApproveStatus(UpdateApproveStatusDTO dto) {
//        Approve approve = approveRepository.findById(dto.getApproveNum())
//                .orElseThrow(() -> new IllegalArgumentException("해당 approve가 존재하지 않습니다."));
//
//        switch (dto.getAction()) {
//            case "permit" -> approve.setApprovePermitCount(approve.getApprovePermitCount() + 1);
//            case "refuse" -> approve.setApproveRefuseCount(approve.getApproveRefuseCount() + 1);
//            default -> throw new IllegalArgumentException("허용되지 않은 액션입니다.");
//        }
//
//        approveRepository.save(approve);
//    }
//}

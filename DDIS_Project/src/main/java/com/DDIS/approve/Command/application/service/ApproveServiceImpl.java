package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.approve.Command.domain.aggregate.Entity.ApproveId;
import com.DDIS.approve.Command.domain.repository.ApproveRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveServiceImpl implements ApproveService {
    RoomRepository roomRepository;
    ApproveRepository approveRepository;

    @Autowired
    public ApproveServiceImpl(RoomRepository roomRepository, ApproveRepository approveRepository) {
        this.roomRepository = roomRepository;
        this.approveRepository = approveRepository;
    }


    @Override
    public Long createApprove(CreateApproveDTO approveDTO) {
        Approve approve = Approve.builder()
                .memberShareTodoNum(approveDTO.getMemberShareTodoNum())
                .memberNum(approveDTO.getMemberNum())
                .approveTitle(approveDTO.getApproveTitle())
                .approveContent(approveDTO.getApproveContent())
                .approveTime(approveDTO.getApproveTime())
                .approvePermitCount(0)
                .approveRefuseCount(0)
                .build();

        Approve saved = approveRepository.save(approve);
        return saved.getApproveNum();

    }

    @Override
    public void updateApproveStatus(UpdateApproveStatusDTO dto) {
        ApproveId id = new ApproveId(dto.getApproveNum(), dto.getMemberShareTodoNum(), dto.getMemberNum());

        Approve approve = approveRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 approve가 존재하지 않습니다."));

        switch (dto.getAction()) {
            case "permit" -> approve.setApprovePermitCount(approve.getApprovePermitCount() + 1);
            case "refuse" -> approve.setApproveRefuseCount(approve.getApproveRefuseCount() + 1);
            default -> throw new IllegalArgumentException("허용되지 않은 액션입니다.");
        }

        approveRepository.save(approve);
    }
}

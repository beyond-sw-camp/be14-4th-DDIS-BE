package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
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
    public void createApprove(CreateApproveDTO approveDTO) {

    }
}

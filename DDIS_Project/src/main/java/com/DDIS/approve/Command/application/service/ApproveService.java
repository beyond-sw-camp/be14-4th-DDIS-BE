package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import org.springframework.stereotype.Service;


public interface ApproveService {
    void createApprove(CreateApproveDTO approveDTO);
}

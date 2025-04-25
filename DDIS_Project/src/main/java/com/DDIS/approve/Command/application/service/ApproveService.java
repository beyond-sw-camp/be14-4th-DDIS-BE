package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import org.springframework.stereotype.Service;


public interface ApproveService {
    Long createApprove(CreateApproveDTO approveDTO);

    void updateApproveStatus(UpdateApproveStatusDTO dto);
}

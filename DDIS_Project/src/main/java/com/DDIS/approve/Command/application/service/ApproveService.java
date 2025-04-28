package com.DDIS.approve.Command.application.service;

import com.DDIS.approve.Command.application.dto.ApproveResponseDTO;
import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApproveService {
    Long createApprove(CreateApproveDTO approveDTO);

    void updateApproveStatus(UpdateApproveStatusDTO dto);

    List<ApproveResponseDTO> getAllApproves();
    ApproveResponseDTO getApprove(Long id);
}

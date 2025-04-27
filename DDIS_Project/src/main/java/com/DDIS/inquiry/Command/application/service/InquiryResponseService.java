package com.DDIS.inquiry.Command.application.service;

import com.DDIS.inquiry.Command.application.dto.InquiryRequestDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseRequestDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseResponseDTO;

public interface InquiryResponseService {

    InquiryResponseResponseDTO createResponse(InquiryResponseRequestDTO requestDTO);

    void updateResponse(Long responseNum, InquiryResponseRequestDTO dto);

    void deleteResponse(Long responseNum);
}

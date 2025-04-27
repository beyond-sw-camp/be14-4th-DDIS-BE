package com.DDIS.inquiry.Command.application.service;

import com.DDIS.inquiry.Command.application.dto.InquiryRequestDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseDTO;

public interface InquiryService {

    InquiryResponseDTO createInquiry(InquiryRequestDTO requestDTO);

    void updateInquiry(Long inquiryNum, InquiryRequestDTO dto);

    void deleteInquiry(Long inquiryNum);


}

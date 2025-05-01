package com.DDIS.inquiry.Query.service;

import com.DDIS.inquiry.Query.dao.InquiryResponseMapper;
import com.DDIS.inquiry.Query.dto.InquiryResponseQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryResponseQueryService {

    private final InquiryResponseMapper inquiryResponseMapper;

    @Autowired
    public InquiryResponseQueryService(InquiryResponseMapper inquiryResponseMapper) {
        this.inquiryResponseMapper = inquiryResponseMapper;
    }

    public List<InquiryResponseQueryDTO> getAllResponses() {
        return inquiryResponseMapper.selectAllResponses();
    }

    public InquiryResponseQueryDTO getResponse(Long inquiryNum) {
        return inquiryResponseMapper.selectResponse(inquiryNum);
    }
}

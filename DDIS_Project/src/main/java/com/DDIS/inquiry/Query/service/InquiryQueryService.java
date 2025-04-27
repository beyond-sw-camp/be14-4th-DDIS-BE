package com.DDIS.inquiry.Query.service;

import com.DDIS.inquiry.Query.dao.InquiryMapper;
import com.DDIS.inquiry.Query.dto.InquiryQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryQueryService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryQueryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public List<InquiryQueryDTO> getAllInquiry() {
        return inquiryMapper.selectAllInquiry();
    }

    public InquiryQueryDTO getInquiryDetail(Long inquiryNum) {
        return inquiryMapper.selectInquiryByNum(inquiryNum);
    }
}

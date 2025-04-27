package com.DDIS.inquiry.Query.controller;

import com.DDIS.inquiry.Query.dto.InquiryQueryDTO;
import com.DDIS.inquiry.Query.dto.InquiryResponseQueryDTO;
import com.DDIS.inquiry.Query.service.InquiryQueryService;
import com.DDIS.inquiry.Query.service.InquiryResponseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inquiry")
public class InquiryQueryController {

    private final InquiryQueryService inquiryQueryService;
    private final InquiryResponseQueryService inquiryResponseQueryService;

    @Autowired
    public InquiryQueryController(InquiryQueryService inquiryQueryService, InquiryResponseQueryService inquiryResponseQueryService) {
        this.inquiryQueryService = inquiryQueryService;
        this.inquiryResponseQueryService = inquiryResponseQueryService;
    }

    // 전체 문의사항 조회
    @GetMapping
    public ResponseEntity<List<InquiryQueryDTO>> getAllInquiry() {
        List<InquiryQueryDTO> list = inquiryQueryService.getAllInquiry();
        return ResponseEntity.ok(list);
    }

    // 특정 문의사항 조회
    @GetMapping("/{inquiryNum}")
    public ResponseEntity<InquiryQueryDTO> getInquiryByInquiryNum(@PathVariable("inquiryNum") Long inquiryNum) {
        InquiryQueryDTO result= inquiryQueryService.getInquiryDetail(inquiryNum);
        return ResponseEntity.ok(result);
    }

    // 답변 전체 조회
    @GetMapping("/response")
    public List<InquiryResponseQueryDTO> getAllResponses() {
        return inquiryResponseQueryService.getAllResponses();
    }

    // 답변 단일 조회
    @GetMapping("/response/{responseNum}")
    public InquiryResponseQueryDTO getResponse(@PathVariable Long responseNum) {
        return inquiryResponseQueryService.getResponse(responseNum);
    }
}

package com.DDIS.inquiry.Command.application.controller;

import com.DDIS.inquiry.Command.application.dto.InquiryRequestDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseRequestDTO;
import com.DDIS.inquiry.Command.application.service.InquiryResponseService;
import com.DDIS.inquiry.Command.application.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;
    private final InquiryResponseService inquiryResponseService;

    // 문의사항 등록
    @PostMapping("/create")
    public InquiryResponseDTO createInquiry(@RequestBody InquiryRequestDTO requestDTO) {
        return inquiryService.createInquiry(requestDTO);
    }

    // 문의사항 수정
    @PutMapping("/update/{inquiryNum}")
    public ResponseEntity<String> updateInquiry(@PathVariable Long inquiryNum,
                                               @RequestBody InquiryRequestDTO dto) {
        inquiryService.updateInquiry(inquiryNum, dto);
        return ResponseEntity.ok("문의사항 수정 완료");
    }

    // 문의사항 삭제
    @DeleteMapping("/delete/{inquiryNum}")
    public void deleteInquiry(@PathVariable Long inquiryNum) {
        inquiryService.deleteInquiry(inquiryNum);
    }

    // 답변 등록
    @PostMapping("/create/response")
    public void createResponse(@RequestBody InquiryResponseRequestDTO requestDTO) {
        inquiryResponseService.createResponse(requestDTO);
    }

    // 답변 수정
    @PutMapping("/update/response/{responseNum}")
    public ResponseEntity<String> updateResponse(@PathVariable Long responseNum,
                                                @RequestBody InquiryResponseRequestDTO dto) {
        inquiryResponseService.updateResponse(responseNum, dto);
        return ResponseEntity.ok("문의사항 수정 완료");
    }

    // 답변 삭제
    @DeleteMapping("/delete/response/{responseNum}")
    public void deleteResponse(@PathVariable Long responseNum) {
        inquiryResponseService.deleteResponse(responseNum);
    }
}
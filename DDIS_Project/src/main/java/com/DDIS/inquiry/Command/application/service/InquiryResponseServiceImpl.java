package com.DDIS.inquiry.Command.application.service;

import com.DDIS.inquiry.Command.application.dto.*;
import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryResponseEntity;
import com.DDIS.inquiry.Command.domain.repository.InquiryResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class InquiryResponseServiceImpl implements InquiryResponseService {

    private final InquiryResponseRepository inquiryResponseRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public InquiryResponseResponseDTO createResponse(InquiryResponseRequestDTO requestDTO) {
        String now = LocalDateTime.now().format(FORMATTER);

        InquiryResponseEntity inquiryResponse = requestDTO.toEntity();
        inquiryResponse.setInquiryNum(requestDTO.getInquiryNum());
        inquiryResponse.setResponseContent(requestDTO.getResponseContent());
        inquiryResponse.setResponseTime(now);

        InquiryResponseEntity savedEntity = inquiryResponseRepository.save(inquiryResponse);

        return InquiryResponseResponseDTO.fromEntity(savedEntity);
    }

    @Override
    public void updateResponse(Long responseNum, InquiryResponseRequestDTO dto) {
        InquiryResponseEntity response = inquiryResponseRepository.findById(responseNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의사항이 존재하지 않습니다."));

        response.setResponseContent(dto.getResponseContent());
        response.setResponseTime(String.valueOf(LocalDateTime.now()));

        inquiryResponseRepository.save(response);
    }

    // 답변 삭제
    public void deleteResponse(Long responseNum) {
        inquiryResponseRepository.deleteById(responseNum);
    }
}

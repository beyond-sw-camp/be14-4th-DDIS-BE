package com.DDIS.inquiry.Command.application.service;

import com.DDIS.inquiry.Command.application.dto.InquiryRequestDTO;
import com.DDIS.inquiry.Command.application.dto.InquiryResponseDTO;
import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
import com.DDIS.inquiry.Command.domain.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    // 문의사항 등록
    @Override
    public InquiryResponseDTO createInquiry(InquiryRequestDTO requestDTO) {
        String now = LocalDateTime.now().format(FORMATTER);

        InquiryEntity inquiry = requestDTO.toEntity();

        inquiry.setInquiryTitle(requestDTO.getInquiryTitle());
        inquiry.setInquiryContent(requestDTO.getInquiryContent());
        inquiry.setInquiryTime(now);

        InquiryEntity savedEntity = inquiryRepository.save(inquiry);
        return InquiryResponseDTO.fromEntity(savedEntity);
    }

    @Override
    public void updateInquiry(Long inquiryNum, InquiryRequestDTO dto) {
        InquiryEntity post = inquiryRepository.findById(inquiryNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의사항이 존재하지 않습니다."));

        post.setInquiryTitle(dto.getInquiryTitle());
        post.setInquiryContent(dto.getInquiryContent());
        post.setInquiryTime(String.valueOf(LocalDateTime.now()));

        inquiryRepository.save(post);
    }

    // 문의사항 삭제
    @Override
    public void deleteInquiry(Long inquiryNum) {
        inquiryRepository.deleteById(inquiryNum);
    }
}

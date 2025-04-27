package com.DDIS.inquiry.Command.application.dto;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryResponseDTO {
    private Long inquiryNum;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryTime;
    private Long clientNum;

    // Entity -> DTO 변환
    public static InquiryResponseDTO fromEntity(InquiryEntity entity) {
        return InquiryResponseDTO.builder()
                .inquiryNum(entity.getInquiryNum())
                .inquiryTitle(entity.getInquiryTitle())
                .inquiryContent(entity.getInquiryContent())
                .inquiryTime(entity.getInquiryTime())
                .clientNum(entity.getClientNum())
                .build();
    }
}
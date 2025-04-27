package com.DDIS.inquiry.Command.application.dto;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryResponseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryResponseResponseDTO {

    private Long responseNum;
    private Long inquiryNum;
    private String responseContent;
    private String responseTime;

    // Entity -> DTO 변환
    public static InquiryResponseResponseDTO fromEntity(InquiryResponseEntity entity) {
        return InquiryResponseResponseDTO.builder()
                .responseNum(entity.getResponseNum())
                .inquiryNum(entity.getInquiryNum())
                .responseContent(entity.getResponseContent())
                .responseTime(entity.getResponseTime())
                .build();
    }
}
package com.DDIS.inquiry.Command.application.dto;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryResponseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryResponseRequestDTO {

    private Long inquiryNum;
    private String responseContent;
    private String responseTime;

    // DTO -> Entity 변환
    public InquiryResponseEntity toEntity() {
        return InquiryResponseEntity.builder()
                .inquiryNum(this.inquiryNum)
                .responseContent(this.responseContent)
                .responseTime(this.responseTime)
                .build();
    }
}
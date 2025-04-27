package com.DDIS.inquiry.Command.application.dto;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryRequestDTO {
    private String inquiryTitle;
    private String inquiryContent;
//    private String inquiryTime;
    private Long clientNum;

    public InquiryEntity toEntity() {
        return InquiryEntity.builder()
                .inquiryTitle(this.inquiryTitle)
                .inquiryContent(this.inquiryContent)
                .clientNum(this.clientNum)   // 여기 꼭 세팅돼야 함!!!
                .build();
    }

}
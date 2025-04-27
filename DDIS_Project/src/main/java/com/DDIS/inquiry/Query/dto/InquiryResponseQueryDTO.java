package com.DDIS.inquiry.Query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryResponseQueryDTO {
    private Long responseNum;
    private Long inquiryNum;
    private String responseContent;
    private String responseTime;
}

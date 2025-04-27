package com.DDIS.inquiry.Query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryQueryDTO {
    private Long inquiryNum;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryTime;
    private Long clientNum;
}

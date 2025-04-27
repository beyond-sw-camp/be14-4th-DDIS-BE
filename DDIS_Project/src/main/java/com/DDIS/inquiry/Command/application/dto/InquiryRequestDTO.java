package com.DDIS.inquiry.Command.application.dto;

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
    private String inquiryTime;
    private Long clientNum;
}

package com.DDIS.applicant.Query.dto;
// 신청자 1명의 정보를 담는 dto

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantResponseDTO {
    private Long clientNum;
    private String clientName;
}

package com.DDIS.applicant.Query.dto;
// 신청자 전체 리스트 + 총 신청 인원 count

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantListResponseDTO {
    private int count;
    private List<ApplicantResponseDTO> applicants;
}

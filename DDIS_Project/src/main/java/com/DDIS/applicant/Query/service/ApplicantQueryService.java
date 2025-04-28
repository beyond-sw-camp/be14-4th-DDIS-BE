package com.DDIS.applicant.Query.service;


import com.DDIS.applicant.Query.dto.ApplicantListResponseDTO;

public interface ApplicantQueryService {
    ApplicantListResponseDTO findApplicantsByPostNum(Long postNum);
}
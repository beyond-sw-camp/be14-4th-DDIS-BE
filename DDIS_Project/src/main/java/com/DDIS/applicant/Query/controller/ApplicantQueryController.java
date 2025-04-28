package com.DDIS.applicant.Query.controller;

import com.DDIS.applicant.Query.dto.ApplicantListResponseDTO;
import com.DDIS.applicant.Query.service.ApplicantQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantQueryController {

    private final ApplicantQueryService applicantQueryService;

    @GetMapping("/{postNum}")
    public ApplicantListResponseDTO getApplicantsByPostNum(@PathVariable Long postNum) {
        return applicantQueryService.findApplicantsByPostNum(postNum);
    }
}
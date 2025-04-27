package com.DDIS.applicant.Command.application.service;

public interface ApplicantService {

    // 1. 신청
    void applyToPost(Long postNum, Long clientNum);

    // 2. 신청 취소
    void cancelApplication(Long postNum, Long clientNum);
}

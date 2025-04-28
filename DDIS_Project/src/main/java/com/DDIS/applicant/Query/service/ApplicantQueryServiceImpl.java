package com.DDIS.applicant.Query.service;


import com.DDIS.applicant.Query.dto.ApplicantListResponseDTO;
import com.DDIS.applicant.Query.dto.ApplicantResponseDTO;
import com.DDIS.applicant.Query.mapper.ApplicantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantQueryServiceImpl implements ApplicantQueryService {

        private final ApplicantMapper applicantMapper;

        @Override
        public ApplicantListResponseDTO findApplicantsByPostNum(Long postNum) {

            // 1. 모집글 존재 여부 체크
            if (!applicantMapper.existsPost(postNum)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "모집글을 찾을 수 없습니다.");
            }

            // 2. 신청자 리스트 조회
            List<ApplicantResponseDTO> applicants = applicantMapper.findApplicantsByPostNum(postNum);

            // 3. 결과 반환
            return new ApplicantListResponseDTO(applicants.size(), applicants);
        }
}

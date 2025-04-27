package com.DDIS.applicant.Command.application.service;

import com.DDIS.applicant.Command.domain.aggregate.ApplicantEntity;
import com.DDIS.applicant.Command.domain.aggregate.ApplicantId;
import com.DDIS.applicant.Command.domain.repository.ApplicantRepository;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final PostRepository postRepository;

    // 1. 모집 신청
    @Override
    public void applyToPost(Long postNum, Long clientNum) {
        if (applicantRepository.existsByPostNumAndClientNum(postNum, clientNum)) {
            throw new IllegalStateException("이미 신청한 게시글입니다.");
        }

        applicantRepository.save(
                ApplicantEntity.builder()
                        .postNum(postNum)
                        .clientNum(clientNum)
                        .build()
        );

        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        post.increaseApplicantCount();

        if (post.getApplicantCount() >= post.getRecruitmentLimit()) {
            post.closeRecruitment();
        }
    }

    // 2. 모집 신청 취소
    @Override
    public void cancelApplication(Long postNum, Long clientNum) {
        if (!applicantRepository.existsByPostNumAndClientNum(postNum, clientNum)) {
            throw new IllegalStateException("신청 내역이 존재하지 않습니다.");
        }

        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 모집이 마감된 경우 취소 불가
        if (Boolean.TRUE.equals(post.getIsClosed())) {
            throw new IllegalStateException("모집이 마감된 게시글은 신청 취소가 불가능합니다.");
        }

        ApplicantId id = new ApplicantId(postNum, clientNum);
        applicantRepository.deleteById(id);

        post.decreaseApplicantCount();
    }

}
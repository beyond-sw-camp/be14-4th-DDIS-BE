package com.DDIS.post.Command.domain.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class PostCreateRequestDTO {
    private String postTitle;             // 제목
    private String postContent;           // 내용

    private String recruitmentStartDate;  // 모집 시작일
    private String recruitmentEndDate;    // 모집 종료일

    private String startDate;             // 활동 시작일
    private String endDate;               // 활동 종료일

    private Integer activityTime;         // 활동 기간 (7,14,21,30일 중 하나)
    private Integer recruitmentLimit;     // 모집 인원

    private Boolean isPublic;             // 공개 여부

    private String postPassword;          // 비공개 시 비밀번호

    private Long categoryNum;             // 카테고리 번호
    private Long clientNum;               // 작성자 회원 번호

}



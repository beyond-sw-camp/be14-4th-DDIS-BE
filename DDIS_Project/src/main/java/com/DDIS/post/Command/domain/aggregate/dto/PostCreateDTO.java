package com.DDIS.post.Command.domain.aggregate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateDTO {

    private String postTitle;              // 게시글 제목
    private String postContent;            // 게시글 내용
    private String recruitmentStartDate;   // 모집 시작일
    private String recruitmentEndDate;     // 모집 종료일
    private Integer activityTime;          // 활동 시간
    private Integer recruitmentLimit;      // 모집 인원
    private Boolean isPublic;              // 공개 여부
    private String postPassword;           // 비공개 게시글용 비밀번호 (nullable 가능)
    private Long categoryNum;              // 카테고리 FK
    private Long clientNum;                // 작성자(회원) FK
}

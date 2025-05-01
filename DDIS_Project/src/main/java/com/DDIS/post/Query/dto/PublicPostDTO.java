package com.DDIS.post.Query.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PublicPostDTO {
    private Long postNum;                  // 게시글 번호
    private String postTitle;               // 제목
    private String postContent;             // 내용
    private String recruitmentStartDate;    // 모집 시작일
    private String recruitmentEndDate;      // 모집 마감일
    private int activityTime;               // 활동 기간 (7,14,21,30일 중)
    private int recruitmentLimit;           // 모집 인원
    private Integer applicantCount;         // 현재 신청 인원
    private Boolean isPublic;               // 공개 여부
    private Boolean isClosed;               // 모집 마감 여부

    private String createdDate;       // 작성일
    private String updatedDate;       // 수정일
    private String deleteDate;        // 삭제일
    private String categoryName;             // 카테고리명
    private String clientName;               // 작성자 이름
}

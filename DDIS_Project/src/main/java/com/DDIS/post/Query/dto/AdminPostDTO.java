// (조회용) DB에서 가져올 컬럼들 담을 응답용 객체 작성
package com.DDIS.post.Query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminPostDTO {
    private Long postNum;                  // 게시글 번호
    private String postTitle;              // 제목
    private String postContent;            // 내용
    private String recruitmentStartDate;   // 모집 시작일
    private String recruitmentEndDate;     // 모집 마감일
    private int activityTime;              // 활동 기간 (7,14,21,30일 중)
    private int recruitmentLimit;          // 모집 인원
    private Integer applicantCount;        // 현재 신청 인원 (nullable)
    private Boolean isPublic;              // 공개 여부
    private String password;               // 비공개 비밀번호
    private String categoryName;
    private String clientName;
}
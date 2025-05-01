package com.DDIS.post.Command.domain.aggregate.dto;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDTO {
        private Long postNum;
        private String title;
        private String content;
        private Integer applicants;
        private Integer limit;
        private Boolean isClosed;
        private String categoryName;
        private String writerName;

        private String createdDate;
        private String updatedDate;
        private String deleteDate;

        private String startDate;         // 참여 시작일
        private String endDate;           // 참여 종료일
        private String recruitStartDate;  // 모집 시작일
        private String recruitEndDate;    // 모집 종료일

        @Builder
        public PostResponseDTO(Long postNum, String title, String content,
                               Integer applicants, Integer limit, Boolean isClosed,
                               String categoryName, String writerName,
                               String createdDate, String updatedDate, String deleteDate,
                               String startDate, String endDate,
                               String recruitStartDate, String recruitEndDate) {
                this.postNum = postNum;
                this.title = title;
                this.content = content;
                this.applicants = applicants;
                this.limit = limit;
                this.isClosed = isClosed;
                this.categoryName = categoryName;
                this.writerName = writerName;
                this.createdDate = createdDate;
                this.updatedDate = updatedDate;
                this.deleteDate = deleteDate;
                this.startDate = startDate;
                this.endDate = endDate;
                this.recruitStartDate = recruitStartDate;
                this.recruitEndDate = recruitEndDate;
        }

        public static PostResponseDTO fromEntity(Post post) {
                return PostResponseDTO.builder()
                        .postNum(post.getPostNum())
                        .title(post.getPostTitle())
                        .content(post.getPostContent())
                        .applicants(post.getApplicantCount())
                        .limit(post.getRecruitmentLimit())
                        .isClosed(post.getIsClosed())
                        .categoryName(post.getCategoryNum().getCategoryName())
                        .writerName(post.getClientNum().getClientName())
                        .createdDate(post.getCreatedDate())
                        .updatedDate(post.getUpdatedDate())
                        .deleteDate(post.getDeleteDate())
                        .startDate(post.getRecruitmentStartDate())   // 참여 시작일 = 모집 시작일
                        .endDate(post.getRecruitmentEndDate())       // 참여 종료일 = 모집 종료일
                        .recruitStartDate(post.getRecruitmentStartDate())
                        .recruitEndDate(post.getRecruitmentEndDate())
                        .build();
        }
}
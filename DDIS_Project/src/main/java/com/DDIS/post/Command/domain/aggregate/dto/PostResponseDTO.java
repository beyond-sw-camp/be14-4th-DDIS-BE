//package com.DDIS.post.Command.domain.aggregate.dto;
//
//import com.DDIS.post.Command.domain.aggregate.entity.Post;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class PostResponseDTO {
//        private Long postNum;
//        private String title;
//        private String content;
//        private String period; // 모집 시작일 ~ 종료일
//        private Integer activityTime;
//        private Integer applicants;
//        private Integer limit;
//        private String status; // 모집중 / 모집마감
//        private String categoryName;
//        private String writerName;
//
//        @Builder
//        public PostResponseDTO(Long postNum, String title, String content, String period,
//                               Integer activityTime, Integer applicants, Integer limit,
//                               String status, String categoryName, String writerName) {
//                this.postNum = postNum;
//                this.title = title;
//                this.content = content;
//                this.period = period;
//                this.activityTime = activityTime;
//                this.applicants = applicants;
//                this.limit = limit;
//                this.status = status;
//                this.categoryName = categoryName;
//                this.writerName = writerName;
//        }
//
//        // Entity → DTO 변환
//        @Builder
//        public static PostResponseDTO fromEntity(Post post) {
//                return PostResponseDTO.builder()
//                        .postNum(post.getPostNum())
//                        .title(post.getPostTitle())
//                        .content(post.getPostContent())
//                        .period(post.getRecruitmentStartDate() + " ~ " + post.getRecruitmentEndDate())
//                        .activityTime(post.getActivityTime())
//                        .applicants(post.getApplicantCount())
//                        .limit(post.getRecruitmentLimit())
//                        .status(post.getIsClosed() ? "모집마감" : "모집중")
//                        .categoryName(post.getCategoryNum().getCategoryName())
//                        .writerName(post.getClientNum().getClientName())
//                        .build();
//        }
//}

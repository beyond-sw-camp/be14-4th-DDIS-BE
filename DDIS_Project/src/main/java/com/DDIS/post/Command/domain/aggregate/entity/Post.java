package com.DDIS.post.Command.domain.aggregate.entity;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_num")
    private Long postNum;

    @Column(name = "post_title", nullable = false, length = 255)
    private String postTitle;

    @Column(name = "post_content", nullable = false, length = 255)
    private String postContent;

    @Column(name = "recruitment_start_date", nullable = false)
    private String recruitmentStartDate; // 모집 시작일

    @Column(name = "recruitment_end_date", nullable = false)
    private String recruitmentEndDate; // 모집 마감일

    @Column(name = "activity_time",  nullable = false)
    private Integer activityTime;

    @Column(name = "recruitment_limit", nullable = false)
    private Integer recruitmentLimit;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Builder.Default
    @Column(name = "applicant_count", nullable = false)
    private Integer applicantCount = 0;

    @Column(name = "post_password")
    private String postPassword;

    @Builder.Default
    @Column(name = "is_closed", nullable = false)
    private Boolean isClosed = false;

    @Column(name = "created_date", nullable = false)
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "delete_date")
    private String deleteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_num")
    private PostCategoryEntity categoryNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_num")
    private UserEntity clientNum;

    // 모집글 수정
    public void updatePost(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.updatedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 소프트 삭제
    public void softDelete(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    // 신청자 수 증가
    public void increaseApplicantCount() {
        this.applicantCount += 1;
    }

    // 신청자 수 감소
    public void decreaseApplicantCount() {
        if (this.applicantCount > 0) {
            this.applicantCount -= 1;
        }
    }

    // 모집 마감
    public void closeRecruitment() {
        this.isClosed = true;
    }
}

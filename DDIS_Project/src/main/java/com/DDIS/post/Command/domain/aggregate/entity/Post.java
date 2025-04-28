package com.DDIS.post.Command.domain.aggregate.entity;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long postNum;

        @Column(nullable = false, length = 255)
        private String postTitle;

        @Column(nullable = false, length = 255)
        private String postContent;

        @Column(nullable = false)
        private String recruitmentStartDate;

        @Column(nullable = false)
        private String recruitmentEndDate;

        @Column(nullable = false)
        private Integer activityTime;

        @Column(nullable = false)
        private Integer recruitmentLimit;

        @Column(nullable = false)
        private Boolean isPublic;

        @Column(nullable = false)
        private Integer applicantCount = 0; // 작성 시 무조건 0명

        private String postPassword;

        @Builder.Default
        @Column(nullable = false)
        private Boolean isClosed = false;

        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createdDate;  // 작성일

        @LastModifiedDate
        private LocalDateTime updatedDate;

        @Column
        private String deleteDate; // 삭제일시

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_num")
        private PostCategoryEntity categoryNum;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "client_num")
        private UserEntity clientNum;


    public Post(String postTitle, String postContent, String recruitmentStartDate,
                String recruitmentEndDate, Integer activityTime, Integer recruitmentLimit,
                Boolean isPublic, String postPassword, PostCategoryEntity category, UserEntity client) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
        this.activityTime = activityTime;
        this.recruitmentLimit = recruitmentLimit;
        this.isPublic = isPublic;
        this.applicantCount = 0;
        this.postPassword = postPassword;
        this.isClosed = false;
        this.categoryNum = category;  // ⭐ FK 객체
        this.clientNum = client;      // ⭐ FK 객체
    }

    public void updatePost(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    // 모집게시글 삭제
    public void softDelete(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    // 신청자 수 +1
    public void increaseApplicantCount() {
        this.applicantCount += 1;
    }

    // 신청마감
    public void closeRecruitment() {
        this.isClosed = true;
    }

    // 신청자 수 -1
    public void decreaseApplicantCount() {
        if (this.applicantCount > 0) {
            this.applicantCount -= 1;
        }
    }

}

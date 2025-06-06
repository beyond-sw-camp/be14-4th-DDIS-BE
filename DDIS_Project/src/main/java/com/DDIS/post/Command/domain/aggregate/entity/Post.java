package com.DDIS.post.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
public class Post {

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

        @Column(nullable = false)
        private Boolean isClosed = false;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_num", insertable = false, updatable = false)
        private PostCategory categoryNum;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "client_num", insertable = false, updatable = false)
        private Client clientNum;

    public Post(String postTitle, String postContent, String recruitmentStartDate,
                String recruitmentEndDate, Integer activityTime, Integer recruitmentLimit,
                Boolean isPublic, String postPassword, PostCategory category, Client client) {
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




}

package com.DDIS.post.Command.domain.aggregate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDTO {

    private String postTitle;
    private String postContent;
    private String recruitmentStartDate;
    private String recruitmentEndDate;
    private Integer activityTime;
    private Integer recruitmentLimit;
    private Boolean isPublic;
    private String postPassword;
    private Long categoryNum;
    private Long clientNum;

    @Builder
    public PostCreateRequestDTO(String postTitle, String postContent,
                                String recruitmentStartDate, String recruitmentEndDate,
                                Integer activityTime, Integer recruitmentLimit,
                                Boolean isPublic, String postPassword,
                                Long categoryNum, Long clientNum) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.recruitmentStartDate = recruitmentStartDate;
        this.recruitmentEndDate = recruitmentEndDate;
        this.activityTime = activityTime;
        this.recruitmentLimit = recruitmentLimit;
        this.isPublic = isPublic;
        this.postPassword = postPassword;
        this.categoryNum = categoryNum;
        this.clientNum = clientNum;
    }
}


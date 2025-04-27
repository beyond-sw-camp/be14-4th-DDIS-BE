package com.DDIS.post.Command.domain.aggregate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostCreateRequestDTO {
    private String postTitle;
    private String postContent;
    private String recruitmentEndDate;
    private Integer activityTime;
    private Integer recruitmentLimit;
    private Boolean isPublic;
    private String postPassword;
    private Long categoryNum;
    private Long clientNum;

}



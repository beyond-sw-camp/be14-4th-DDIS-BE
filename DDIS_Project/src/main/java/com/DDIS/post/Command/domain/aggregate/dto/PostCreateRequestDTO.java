package com.DDIS.post.Command.domain.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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
    private String createdDate;

}



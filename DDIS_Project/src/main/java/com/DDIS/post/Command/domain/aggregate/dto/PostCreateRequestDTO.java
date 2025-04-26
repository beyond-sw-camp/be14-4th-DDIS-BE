package com.DDIS.post.Command.domain.aggregate.dto;

import com.DDIS.shareTodo.Command.application.dto.ShareTodoCreateDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<ShareTodoCreateDTO> shareTodos; // ⭐ 추가 : 공동할일 여러개
}

//    @Builder
//    public PostCreateRequestDTO(String postTitle, String postContent,
//                                String recruitmentStartDate, String recruitmentEndDate,
//                                Integer activityTime, Integer recruitmentLimit,
//                                Boolean isPublic, String postPassword,
//                                Long categoryNum, Long clientNum) {
//        this.postTitle = postTitle;
//        this.postContent = postContent;
//        this.recruitmentStartDate = recruitmentStartDate;
//        this.recruitmentEndDate = recruitmentEndDate;
//        this.activityTime = activityTime;
//        this.recruitmentLimit = recruitmentLimit;
//        this.isPublic = isPublic;
//        this.postPassword = postPassword;
//        this.categoryNum = categoryNum;
//        this.clientNum = clientNum;
//    }
//}


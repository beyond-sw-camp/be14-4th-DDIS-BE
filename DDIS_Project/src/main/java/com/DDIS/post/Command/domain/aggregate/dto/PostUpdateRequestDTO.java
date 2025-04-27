package com.DDIS.post.Command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequestDTO {

        private String postTitle;
        private String postContent;
        // 필요하면 모집기간, 모집인원 등도 추가

}

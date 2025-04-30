package com.DDIS.post.Command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResearchDTO {
    private Long postNum;
    private String postTitle;
    private String snippet;  // 검색 하이라이트 부분 추출 (필요하면)



}

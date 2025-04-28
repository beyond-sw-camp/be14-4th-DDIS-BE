package com.DDIS.post.Command.domain.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResearchDTO {
    private Long postNum;
    private String postTitle;
    private String snippet;  // 검색 하이라이트 부분 추출 (필요하면)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;


}

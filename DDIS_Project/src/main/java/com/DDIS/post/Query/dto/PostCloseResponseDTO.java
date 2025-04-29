package com.DDIS.post.Query.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostCloseResponseDTO {
    private Long postNum;
    private Boolean isClosed;
}

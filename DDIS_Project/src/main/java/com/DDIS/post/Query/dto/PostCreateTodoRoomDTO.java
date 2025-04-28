package com.DDIS.post.Query.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostCreateTodoRoomDTO {

        private Long postNum;
        private Long clientNum;
        private String recruitmentEndDate;
        private Integer recruitmentLimit;
        private Integer applicantCount;
        private Boolean isClosed;

}

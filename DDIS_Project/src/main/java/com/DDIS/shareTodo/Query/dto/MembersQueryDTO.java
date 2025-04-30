package com.DDIS.shareTodo.Query.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembersQueryDTO {
    private Long memberNum;
    private Long roomNum;
    private Long postNum;
    private Long clientNum;
}
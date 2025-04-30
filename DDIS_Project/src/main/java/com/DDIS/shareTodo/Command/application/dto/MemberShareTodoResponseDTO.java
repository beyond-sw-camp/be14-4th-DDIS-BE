package com.DDIS.shareTodo.Command.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberShareTodoResponseDTO {
    private Long memberShareTodoNum;
    private Long shareTodoNum;       // 방 번호
    private String shareTodoName;   // 방 제목
}
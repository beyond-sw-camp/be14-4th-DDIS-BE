package com.DDIS.shareTodo.Command.application.dto;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShareTodoDTO {
    private Long shareTodoNum;
    private String shareTodoName;


    public ShareTodoDTO(ShareTodo entity) {
        this.shareTodoNum = entity.getShareTodoNum();
        this.shareTodoName = entity.getShareTodoName();
    }
}

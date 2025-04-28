package com.DDIS.shareTodo.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateShareTodoDTO {
    private Long shareTodoNum;
    private String shareTodoName;
    private Integer pinOrder;
}

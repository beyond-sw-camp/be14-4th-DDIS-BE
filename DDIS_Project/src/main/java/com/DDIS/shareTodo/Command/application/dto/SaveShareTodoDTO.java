package com.DDIS.shareTodo.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveShareTodoDTO {
    private String shareTodoName;
    private Long postNum;
    private int pinOrder;
}
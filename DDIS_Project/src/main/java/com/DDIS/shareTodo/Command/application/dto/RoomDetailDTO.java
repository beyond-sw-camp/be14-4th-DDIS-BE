package com.DDIS.shareTodo.Command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RoomDetailDTO {
    private List<ShareTodoDTO> todoList;
    private List<ApproveDTO> approveList;
}

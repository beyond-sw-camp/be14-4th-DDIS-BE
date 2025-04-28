package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.UpdateShareTodoDTO;

public interface ShareTodoService {
     void updateShareTodo(Long shareTodoNum, UpdateShareTodoDTO dto);

}

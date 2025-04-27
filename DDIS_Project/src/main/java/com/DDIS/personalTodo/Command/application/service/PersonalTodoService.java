package com.DDIS.personalTodo.Command.application.service;

import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;

public interface PersonalTodoService {
    void createPersonalTodo(PersonalTodoCreateRequestDTO requestDTO, Long clientNum);
}

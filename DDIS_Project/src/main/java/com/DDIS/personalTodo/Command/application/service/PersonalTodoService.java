package com.DDIS.personalTodo.Command.application.service;

import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoUpdateRequestDTO;
import jakarta.validation.Valid;

public interface PersonalTodoService {
    void createPersonalTodo(PersonalTodoCreateRequestDTO requestDTO, Long clientNum);

    void updatePersonalTodo(PersonalTodoUpdateRequestDTO requestDTO, Long clientNum);
    void deletePersonalTodo(Long todoNum, Long clientNum);
    void deletePersonalTodoDate(Long todoNum, String todoDate, Long clientNum);

}

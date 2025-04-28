package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.MemberShareTodoResponseDTO;

import java.util.List;

public interface MemberShareTodoService {
    void completeTodo(Long memberShareTodoNum);
    void uncompleteTodo(Long memberShareTodoNum);

    List<MemberShareTodoResponseDTO> getByClientNum(Integer clientNum);
}

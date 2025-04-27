package com.DDIS.shareTodo.Command.application.service;

public interface MemberShareTodoService {
    void completeTodo(Long memberShareTodoNum);
    void uncompleteTodo(Long memberShareTodoNum);
}

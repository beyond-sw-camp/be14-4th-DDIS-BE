package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;

import java.util.List;

public interface GptService {
    List<ShareTodo> generateTodoList(Rooms room, String topic);
}

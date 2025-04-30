package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.GenerateTodoDatesRequest;

import java.util.List;

public interface MemberShareTodoDateService {
    void generateTodoDates(List<GenerateTodoDatesRequest> request);
}

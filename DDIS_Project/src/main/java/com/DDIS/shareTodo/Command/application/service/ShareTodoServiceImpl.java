package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.UpdateShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import com.DDIS.shareTodo.Command.domain.repository.ShareTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShareTodoServiceImpl implements ShareTodoService {

    private final ShareTodoRepository shareTodoRepository;
    @Override
    public void updateShareTodo(Long shareTodoNum, UpdateShareTodoDTO dto) {
        ShareTodo todo = shareTodoRepository.findById(shareTodoNum)
                .orElseThrow(() -> new IllegalArgumentException("공동Todo 없음"));

        if (dto.getShareTodoName() != null) {
            todo.setShareTodoName(dto.getShareTodoName());
        }

        shareTodoRepository.save(todo);


    }
}

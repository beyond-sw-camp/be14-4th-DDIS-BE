package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MemberShareTodoServiceImpl implements MemberShareTodoService{
    private final MemberShareTodoRepository memberShareTodoRepository;

    @Override
    @Transactional
    public void completeTodo(Long memberShareTodoNum) {
        MemberShareTodo todo = memberShareTodoRepository.findById(memberShareTodoNum)
                .orElseThrow(() -> new IllegalArgumentException("공동투두 없음"));

        todo.setCompleted(true);
        todo.setCompletionTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    @Transactional
    public void uncompleteTodo(Long memberShareTodoNum) {
        MemberShareTodo todo = memberShareTodoRepository.findById(memberShareTodoNum)
                .orElseThrow(() -> new IllegalArgumentException("공동투두 없음"));

        todo.setCompleted(false);
        todo.setCompletionTime(null);
    }
}

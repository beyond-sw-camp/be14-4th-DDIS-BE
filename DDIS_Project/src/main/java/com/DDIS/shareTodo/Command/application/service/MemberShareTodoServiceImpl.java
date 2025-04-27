package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.MemberShareTodoResponseDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<MemberShareTodoResponseDTO> getByClientNum(Integer clientNum) {
        List<MemberShareTodo> list = memberShareTodoRepository.findByMemberNum_ClientNum(clientNum);
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    private MemberShareTodoResponseDTO toDTO(MemberShareTodo memberShareTodo) {
        return MemberShareTodoResponseDTO.builder()
                .memberShareTodoNum(memberShareTodo.getMemberShareTodoNum())
                .shareTodoNum(memberShareTodo.getShareTodoNum().getShareTodoNum())   // 방 번호
                .shareTodoName(memberShareTodo.getShareTodoNum().getShareTodoName())         // 방 제목
                .isCompleted(memberShareTodo.isCompleted())                          // 완료 여부
                .completionTime(memberShareTodo.getCompletionTime())                 // 완료 시간
                .build();
    }
}

package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.GenerateTodoDatesRequest;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberShareTodoDateServiceImpl implements MemberShareTodoDateService {
    private final MemberShareTodoDateRepository repository;
    private final MemberShareTodoRepository memberShareTodoRepository;

    public void generateTodoDates(List<GenerateTodoDatesRequest> requestList) {

        List<MemberShareTodoDate> allDates = new ArrayList<>();

        // 1. shareTodoNum 기준으로 모든 MemberShareTodo 가져오기
        for (GenerateTodoDatesRequest request : requestList) {
            List<MemberShareTodo> memberShareTodos = memberShareTodoRepository.findByShareTodo_ShareTodoNum(request.getShareTodoNum());

            int year = request.getYear();
            int month = request.getMonth();
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            for (MemberShareTodo memberShareTodo : memberShareTodos) {
                Long memberShareTodoNum = memberShareTodo.getMemberShareTodoNum();

                for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                    int dayOfWeek = date.getDayOfWeek().getValue();

                    if (request.getSelectedWeekdays().contains(dayOfWeek)) {
                        allDates.add(MemberShareTodoDate.builder()
                                .todoDate(date.toString())
                                .memberShareTodoNum(memberShareTodoNum)
                                .isDone(false)
                                .isPublic(false)
                                .pinOrder(0)
                                .build());
                    }
                }
            }
        }

        repository.saveAll(allDates);
    }
}

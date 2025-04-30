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
public class MemberShareTodoDateServiceImpl implements MemberShareTodoDateService{
    private final MemberShareTodoDateRepository repository;
    private final MemberShareTodoRepository memberShareTodoRepository;

    public void generateTodoDates(GenerateTodoDatesRequest request) {
        // 1. shareTodoNum 기준으로 모든 MemberShareTodo 가져오기
        List<MemberShareTodo> memberShareTodos = memberShareTodoRepository.findByShareTodo_ShareTodoNum(request.getShareTodoNum());

        int year = request.getYear();
        int month = request.getMonth();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<MemberShareTodoDate> dates = new ArrayList<>();

        // 2. 가져온 MemberShareTodo 각각에 대해 날짜 생성
        for (MemberShareTodo memberShareTodo : memberShareTodos) {
            Long memberShareTodoNum = memberShareTodo.getMemberShareTodoNum(); // 멤버별 투두 번호

            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                int dayOfWeek = date.getDayOfWeek().getValue(); // 1=월 ~ 7=일

                if (request.getSelectedWeekdays().contains(dayOfWeek)) {
                    MemberShareTodoDate todoDate = MemberShareTodoDate.builder()
                            .todoDate(date.toString())
                            .memberShareTodoNum(memberShareTodoNum)
                            .isDone(false)
                            .isPublic(false)
                            .pinOrder(0)
                            .build();
                    dates.add(todoDate);
                }
            }
        }

        // 3. 한 번에 저장
        repository.saveAll(dates);
    }
}

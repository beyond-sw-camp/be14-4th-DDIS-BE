package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.GenerateTodoDatesRequest;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberShareTodoDateServiceImpl implements MemberShareTodoDateService{
    private final MemberShareTodoDateRepository repository;

    public void generateTodoDates(GenerateTodoDatesRequest request) {
        // 시작일: 해당 연도, 해당 월의 1일
        int year = request.getYear();
        int month = request.getMonth();
        LocalDate start = LocalDate.of(year, month, 1);

        // 종료일: 해당 연도, 해당 월의 마지막 날
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<MemberShareTodoDate> dates = new ArrayList<>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue(); // 1=월 ~ 7=일

            if (request.getSelectedWeekdays().contains(dayOfWeek)) {
                MemberShareTodoDate todoDate = MemberShareTodoDate.builder()
                        .todoDate(date.toString())
                        .memberShareTodoNum(request.getMemberShareTodoNum())
                        .isDone(false)
                        .isPublic(false)
                        .pinOrder(0)
                        .build();
                dates.add(todoDate);
            }
        }

        repository.saveAll(dates);
    }
}

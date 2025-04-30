package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{
    private final MemberShareTodoDateRepository memberShareTodoDateRepository;

    @Override
    public List<DailyShareTodoDTO> getTodosByDate(Long roomNum, Long clientNum, String date) {
        return memberShareTodoDateRepository.findDailyTodos(roomNum, clientNum, date);
    }
}

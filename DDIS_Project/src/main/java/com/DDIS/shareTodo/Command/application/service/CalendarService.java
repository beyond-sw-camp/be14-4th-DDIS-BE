package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;

import java.util.List;

public interface CalendarService {
     List<DailyShareTodoDTO> getTodosByDate(Long roomNum, Long clientNum, String date);

}

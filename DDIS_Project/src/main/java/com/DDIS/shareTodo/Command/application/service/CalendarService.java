package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;

import java.util.List;
import java.util.Map;

public interface CalendarService {
     List<DailyShareTodoDTO> getTodosByDate(Long roomNum, Long clientNum, String date);

    Map<String, String> getDateColorMap(Long roomNum);
}

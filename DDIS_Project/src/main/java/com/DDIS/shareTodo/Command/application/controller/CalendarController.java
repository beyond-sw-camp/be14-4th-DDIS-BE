package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.application.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/room/{roomNum}/todos")
    public ResponseEntity<List<DailyShareTodoDTO>> getRoomTodosByDate(
            @PathVariable Long roomNum,
            @RequestParam Long clientNum,
            @RequestParam String date
    ) {
        List<DailyShareTodoDTO> todos = calendarService.getTodosByDate(roomNum, clientNum, date);
        return ResponseEntity.ok(todos);
    }
}

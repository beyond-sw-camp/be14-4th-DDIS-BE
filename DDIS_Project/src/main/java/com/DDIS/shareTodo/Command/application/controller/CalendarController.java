package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.application.dto.DoneLogDTO;
import com.DDIS.shareTodo.Command.application.dto.DoneLogResponse;
import com.DDIS.shareTodo.Command.application.service.CalendarService;
import com.DDIS.shareTodo.Command.application.service.MemberShareTodoDateService;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final MemberShareTodoDateService memberShareTodoDateService;

    @GetMapping("/room/{roomNum}/todos")
    public ResponseEntity<List<DailyShareTodoDTO>> getRoomTodosByDate(
            @PathVariable Long roomNum,
            @RequestParam Long clientNum,
            @RequestParam String date
    ) {
        List<DailyShareTodoDTO> todos = calendarService.getTodosByDate(roomNum, clientNum, date);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/room/{roomNum}/done-logs")
    public ResponseEntity<List<DoneLogResponse>> getDoneLogs(
            @PathVariable Long roomNum,
            @RequestParam String date) {
        List<DoneLogResponse> logs = memberShareTodoDateService.getDoneLogs(roomNum, date);
        return ResponseEntity.ok(logs);
    }
}



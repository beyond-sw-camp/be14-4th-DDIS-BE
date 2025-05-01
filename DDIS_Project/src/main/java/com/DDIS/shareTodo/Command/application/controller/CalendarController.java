package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.application.dto.DoneLogDTO;
import com.DDIS.shareTodo.Command.application.dto.DoneLogResponse;
import com.DDIS.shareTodo.Command.application.service.CalendarService;
import com.DDIS.shareTodo.Command.application.service.MemberShareTodoDateService;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoDateRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    private final MemberShareTodoDateService memberShareTodoDateService;
    private final MemberShareTodoDateRepository memberShareTodoDateRepository;
    private final RoomRepository roomRepository;

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

    @GetMapping("/room/{roomNum}/color-mapping")
    public ResponseEntity<Map<String, String>> getDateColorMapping(
            @PathVariable Long roomNum
    ) {
        Map<String, String> colorMapping = calendarService.getDateColorMap(roomNum);
        return ResponseEntity.ok(colorMapping);
    }

}



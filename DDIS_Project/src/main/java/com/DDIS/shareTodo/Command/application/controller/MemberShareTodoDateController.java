package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.GenerateTodoDatesRequest;
import com.DDIS.shareTodo.Command.application.service.MemberShareTodoDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member-share-todo-date")
@RequiredArgsConstructor
public class MemberShareTodoDateController {
    private final MemberShareTodoDateService service;

    @PostMapping("/generate")
    public ResponseEntity<Void> generateTodoDates(@RequestBody GenerateTodoDatesRequest request) {
        service.generateTodoDates(request);
        return ResponseEntity.ok().build();
    }
}

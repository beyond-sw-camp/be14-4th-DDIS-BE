package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.approve.Command.application.dto.GenerateTodoRequest;
import com.DDIS.shareTodo.Command.application.service.GptService;
import com.DDIS.shareTodo.Command.application.service.RoomService;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @PostMapping("/generate-todos")
    public List<ShareTodo> generateTodos(@RequestBody GenerateTodoRequest request) {
        String topic = request.getTopic();
        return gptService.generateTodoList(topic);
    }
}


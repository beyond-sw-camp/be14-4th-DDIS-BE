package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.service.GptService;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @PostMapping("/generate-todos")
    public List<ShareTodo> generateTodos(@RequestBody String topic) {
        return gptService.generateTodoList(topic);
    }
}

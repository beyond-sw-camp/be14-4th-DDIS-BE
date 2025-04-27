package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.dto.SaveShareTodoDTO;
import com.DDIS.shareTodo.Command.application.service.GptService;
import com.DDIS.shareTodo.Command.application.service.RoomService;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    private Environment env;
    private RoomService roomService;
    private ModelMapper modelMapper;
    private GptService gptService;

    @Autowired
    public RoomController(Environment env, RoomService roomService, ModelMapper modelMapper, GptService gptService) {
        this.env = env;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
        this.gptService = gptService;
    }

    @GetMapping("/health")
    public String status() {
        return "연결정상" + env.getProperty("server.port");
    }

    @PostMapping("/room")
    public ResponseEntity<List<ShareTodo>> createRoom(@RequestBody CreateShareRoomDTO roomDTO) {


        List<ShareTodo> generatedTodos = roomService.createRoom(roomDTO);
        return ResponseEntity.ok(generatedTodos);



    }

    @PostMapping("/room/share-todo")
    public ResponseEntity<?> saveShareTodos(@RequestBody List<SaveShareTodoDTO> todoList) {
        roomService.saveShareTodos(todoList);
        return ResponseEntity.ok("공동 Todo 저장 완료!");
    }







}

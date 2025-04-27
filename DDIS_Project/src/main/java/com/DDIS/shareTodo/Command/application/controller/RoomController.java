package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.dto.SaveShareTodoDTO;
import com.DDIS.shareTodo.Command.application.service.GptService;
import com.DDIS.shareTodo.Command.application.service.RoomService;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
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

    @Autowired
    public RoomController(Environment env, RoomService roomService) {
        this.env = env;
        this.roomService = roomService;

    }

    @GetMapping("/health")
    public String status() {
        return "연결정상" + env.getProperty("server.port");
    }

    @PostMapping("/room")
    public ResponseEntity<Rooms> createRoom(@RequestBody CreateShareRoomDTO roomDTO) {


        Rooms room = roomService.createRoom(roomDTO);
        return ResponseEntity.ok(room);


    }

    @PostMapping("/room/share-todo")
    public ResponseEntity<?> saveShareTodos(@RequestBody List<SaveShareTodoDTO> todoList) {
        roomService.saveShareTodos(todoList);
        return ResponseEntity.ok("공동 Todo 저장 완료!");
    }







}

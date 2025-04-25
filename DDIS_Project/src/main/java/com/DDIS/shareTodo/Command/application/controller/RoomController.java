package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private Environment env;
    private RoomService roomService;
    private ModelMapper modelMapper;

    @Autowired
    public RoomController(Environment env, RoomService roomService, ModelMapper modelMapper) {
        this.env = env;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health")
    public String status() {
        return "연결정상" + env.getProperty("server.port");
    }

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody CreateShareRoomDTO roomDTO) {


        roomService.createRoom(roomDTO);
    }





}

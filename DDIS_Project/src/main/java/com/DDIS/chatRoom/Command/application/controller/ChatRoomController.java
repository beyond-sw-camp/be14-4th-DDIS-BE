package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.service.ChatRoomService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // 모든 채팅방 조회
    @GetMapping
    public List<ChatRoomEntity> getRooms() {
        return chatRoomService.getAllRooms();
    }

    // 채팅방 생성
    @PostMapping
    public ChatRoomEntity createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    // 채팅방ID로 조회
    @GetMapping("/{roomId}")
    public ChatRoomEntity getRoom(@PathVariable String roomId) {
        return chatRoomService.getRoom(roomId);
    }
}

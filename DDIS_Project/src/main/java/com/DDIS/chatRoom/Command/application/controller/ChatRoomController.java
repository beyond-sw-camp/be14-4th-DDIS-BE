package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // ✅ 채팅방 생성
    @PostMapping
    public ChatRoomResponseDTO createRoom(@RequestBody ChatRoomRequestDTO requestDTO) {
        return chatRoomService.createRoom(requestDTO);
    }

    // ✅ 전체 채팅방 목록 조회
    @GetMapping
    public List<ChatRoomResponseDTO> getAllRooms() {
        return chatRoomService.getAllRooms();
    }

    // ✅ 특정 채팅방 조회
    @GetMapping("/{chatroomNum}")
    public ChatRoomResponseDTO getRoom(@PathVariable Long chatroomNum) {
        return chatRoomService.getRoomById(chatroomNum);
    }
}

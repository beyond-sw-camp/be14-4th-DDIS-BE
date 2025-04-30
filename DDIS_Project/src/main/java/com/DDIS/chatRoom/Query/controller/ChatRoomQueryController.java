package com.DDIS.chatRoom.Query.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Query.dto.ChatRoomQueryDTO;
import com.DDIS.chatRoom.Query.service.ChatRoomLogQueryService;
import com.DDIS.chatRoom.Query.service.ChatRoomQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-room")
@RequiredArgsConstructor
public class ChatRoomQueryController {

    private final ChatRoomQueryService chatRoomQueryService;


    // ✅ 전체 채팅방 목록 조회
    @GetMapping("/all-chat-room")
    public List<ChatRoomQueryDTO> getAllRooms() {
        return chatRoomQueryService.getAllRooms();
    }

    // ✅ 특정 채팅방 조회
    @GetMapping("/{chatroomNum}")
    public ChatRoomQueryDTO getRoom(@PathVariable Long chatroomNum) {
        return chatRoomQueryService.getRoomById(chatroomNum);
    }

}

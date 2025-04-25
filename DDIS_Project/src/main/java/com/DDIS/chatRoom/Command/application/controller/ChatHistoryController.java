package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatHistoryController {

    private final ChatRoomLogService chatRoomLogService;

    public ChatHistoryController(ChatRoomLogService chatRoomLogService) {
        this.chatRoomLogService = chatRoomLogService;
    }

    @GetMapping("/messages/{roomId}")
    public List<ChatRoomLogResponseDTO> getMessages(@PathVariable String roomId) {
        return chatRoomLogService.getMessagesByRoomAsDTO(roomId);
    }
}

package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.service.ChatMessageService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatMessageEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatHistoryController {

    private final ChatMessageService chatMessageService;

    public ChatHistoryController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/messages/{roomId}")
    public List<ChatMessageEntity> getMessages(@PathVariable String roomId) {
        return chatMessageService.getMessagesByRoom(roomId);
    }
}

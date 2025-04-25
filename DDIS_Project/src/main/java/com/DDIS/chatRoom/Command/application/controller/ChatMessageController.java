package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatMessageDTO;
import com.DDIS.chatRoom.Command.application.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat/message") // 클라이언트가 보내는 주소
    @SendTo("/topic/chatroom")       // 모든 방이 구독
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
        return chatMessageService.processAndSave(message);
    }
}

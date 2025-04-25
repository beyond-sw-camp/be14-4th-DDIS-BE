package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomLogService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatRoomLogController {
    private final ChatRoomLogService chatRoomLogService;
    private final SimpMessagingTemplate messagingTemplate;


    public ChatRoomLogController(ChatRoomLogService chatRoomLogService, SimpMessagingTemplate messagingTemplate) {
        this.chatRoomLogService = chatRoomLogService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/send")
    public void sendMessage(@Payload ChatRoomLogRequestDTO requestDTO) {
        // DB에 저장
        ChatRoomLogResponseDTO responseDTO = chatRoomLogService.saveMessage(requestDTO);

        // 구독자들에게 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chatroom/" + responseDTO.getRoomId(), responseDTO);
    }

    // 특정 채팅방의 메시지 리스트 조회
    @GetMapping("/{roomId}")
    public List<ChatRoomLogResponseDTO> getMessages(@PathVariable String roomId) {
        return chatRoomLogService.getMessagesByRoomAsDTO(roomId);
    }
}

package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomLogService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
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

    @MessageMapping("/pub/chat/send")
    public void sendMessage(@Payload ChatRoomLogRequestDTO requestDTO) {
        // DB에 저장
        ChatRoomLogResponseDTO responseDTO = chatRoomLogService.saveMessage(requestDTO);

        // 구독자들에게 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/sub/chatroom/" + responseDTO.getRoomNum(), responseDTO);
    }

    // 메시지 단건(1개) 삭제
    @DeleteMapping("/delete/{logId}")
    public void deleteMessage(@PathVariable Long logId) {
        chatRoomLogService.deleteMessage(logId);
    }

//    // 채팅방의 모든 메시지 삭제 (선택사항)
//    @DeleteMapping("/delete/room/{roomNum}")
//    public void deleteAllMessagesInRoom(@PathVariable Long roomNum) {
//        chatRoomLogService.deleteMessagesByRoom(roomNum);
//    }

    @MessageMapping("/chat/delete")
    public void deleteMessageViaWebSocket(@Payload Long logId) {
        // DB에서 soft delete
        chatRoomLogService.deleteMessage(logId);

        // 구독자에게 "이 메시지 삭제됐음" 알림 보내기
        messagingTemplate.convertAndSend("/sub/chatroom/delete", logId);
    }

}

package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomReadRequestDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomAccessService;
import com.DDIS.chatRoom.Command.application.service.ChatRoomLogService;
import com.DDIS.chatRoom.Command.application.service.ChatRoomUserService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class  ChatRoomLogController {
    private final ChatRoomLogService chatRoomLogService;
    private final ChatRoomUserService chatRoomUserService;
    private final ChatRoomAccessService chatRoomAccessService;
    private final SimpMessagingTemplate messagingTemplate;


    public ChatRoomLogController(ChatRoomLogService chatRoomLogService, ChatRoomUserService chatRoomUserService, ChatRoomAccessService chatRoomAccessService, SimpMessagingTemplate messagingTemplate) {
        this.chatRoomLogService = chatRoomLogService;
        this.chatRoomUserService = chatRoomUserService;
        this.chatRoomAccessService = chatRoomAccessService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/send")
    public void sendMessage(@Payload ChatRoomLogRequestDTO requestDTO) {

        // ✅ 접근 권한 확인
        boolean allowed = chatRoomAccessService.canAccessChatRoom(requestDTO.getChatRoomNum(), requestDTO.getSender());
        if (!allowed) {
            throw new AccessDeniedException("이 채팅방에 접근할 수 없습니다.");
        }

        // DB에 저장
        chatRoomLogService.saveMessage(requestDTO);

        // 구독자들에게 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/sub/chatroom/" + requestDTO.getChatRoomNum(), requestDTO);
    }

    @MessageMapping("/chat/read")
    public void readMessage(@Payload ChatRoomReadRequestDTO dto) {
        chatRoomUserService.updateLastReadMessage(dto.getRoomId(), dto.getClientId(), dto.getLastReadMessageId());
    }

    // 메시지 단건(1개) 삭제
    @DeleteMapping("/delete/{logId}")
    public void deleteMessage(@PathVariable Long logId) {
        chatRoomLogService.deleteMessage(logId);
    }

    @MessageMapping("/chat/delete")
    public void deleteMessageViaWebSocket(@Payload Long logId) {
        // DB에서 soft delete
        chatRoomLogService.deleteMessage(logId);

        // 구독자에게 "이 메시지 삭제됐음" 알림 보내기
        messagingTemplate.convertAndSend("/sub/chatroom/delete", logId);
    }

}

package com.DDIS.chatRoom.Query.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import com.DDIS.chatRoom.Query.service.ChatRoomLogQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-room-log")
@RequiredArgsConstructor
public class ChatRoomLogQueryController {

    private final ChatRoomLogQueryService chatRoomLogQueryService;


    // 특정 채팅방의 메시지 리스트 조회
    @GetMapping("/{roomNum}")
    public List<ChatRoomLogQueryDTO> getMessages(@PathVariable Long roomNum) {
        return chatRoomLogQueryService.getMessagesByRoomAsDTO(roomNum);
    }

}

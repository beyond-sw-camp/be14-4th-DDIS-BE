package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomSimpleDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService, ChatRoomUserRepository chatRoomUserRepository) {
        this.chatRoomService = chatRoomService;
        this.chatRoomUserRepository = chatRoomUserRepository;
    }

    private final ChatRoomUserRepository chatRoomUserRepository;

    @GetMapping("/my-list")
    public List<ChatRoomSimpleDTO> getMyChatRooms(@RequestParam Long clientNum) {
        List<ChatRoomEntity> rooms = chatRoomUserRepository.findChatRoomsByClientNum(clientNum);
        return rooms.stream()
                .map(ChatRoomSimpleDTO::from)
                .toList();
    }
}

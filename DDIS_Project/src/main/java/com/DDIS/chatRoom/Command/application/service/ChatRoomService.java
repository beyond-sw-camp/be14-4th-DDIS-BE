package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.repository.MembersRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MembersRepository membersRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ChatRoomService(ChatRoomRepository chatRoomRepository, MembersRepository membersRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.membersRepository = membersRepository;
    }

}

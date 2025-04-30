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
    public boolean canAccessChatRoom(Long chatRoomNum, Long clientNum) {
        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatRoomNum)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        Long roomNum = chatRoom.getRoom().getRoomNum();
        return membersRepository.existsByRoomRoomNumAndClientClientNum(roomNum, clientNum);
    }

    // 채팅방 생성
//    public ChatRoomResponseDTO createRoom(ChatRoomRequestDTO dto) {
//        String now = LocalDateTime.now().format(FORMATTER);
//        Rooms room = roomRepository.findById(dto.getChatRoomNum())
//                .orElseThrow(() -> new IllegalArgumentException("방 정보 없음"));
//
//        ChatRoomEntity chatRoom = new ChatRoomEntity();
//        chatRoom.setChatRoomName(dto.getChatRoomName());
//        chatRoom.setChatRoomType(dto.getChatRoomType());
//        chatRoom.setRoomNum(room);
//        chatRoom.setCreatedTime(now);
//
//        ChatRoomEntity saved = chatRoomRepository.save(chatRoom);
//
//        return new ChatRoomResponseDTO(
//                saved.getChatRoomNum(),
//                saved.getChatRoomName(),
//                saved.getChatRoomType(),
//                saved.getRoomNum().getRoomNum(),
//                saved.getCreatedTime()
//        );
//    }
}

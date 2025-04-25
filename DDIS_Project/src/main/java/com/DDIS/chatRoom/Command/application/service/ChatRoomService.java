package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    // 채팅방 생성
    public ChatRoomResponseDTO createRoom(ChatRoomRequestDTO dto) {
        String now = LocalDateTime.now().format(FORMATTER);

        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setChatRoomName(dto.getChatroom_name());
        chatRoom.setChatType(dto.getChatroom_type());
        chatRoom.setRoomNum(dto.getRoom_num());
        chatRoom.setCreatedTime(now);

        ChatRoomEntity saved = chatRoomRepository.save(chatRoom);

        return new ChatRoomResponseDTO(
                saved.getChatRoomId(),
                saved.getChatRoomName(),
                saved.getChatType(),
                saved.getRoomNum(),
                saved.getCreatedTime()
        );
    }

    // 전체 채팅방 목록 조회
    public List<ChatRoomResponseDTO> getAllRooms() {
        return chatRoomRepository.findAll().stream()
                .map(room -> new ChatRoomResponseDTO(
                        room.getChatRoomId(),
                        room.getChatRoomName(),
                        room.getChatType(),
                        room.getRoomNum(),
                        room.getCreatedTime()
                ))
                .collect(Collectors.toList());
    }

    // 특정 채팅방 단건 조회
    public ChatRoomResponseDTO getRoomById(Long chatroomNum) {
        ChatRoomEntity room = chatRoomRepository.findById(chatroomNum)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        return new ChatRoomResponseDTO(
                room.getChatRoomId(),
                room.getChatRoomName(),
                room.getChatType(),
                room.getRoomNum(),
                room.getCreatedTime()
        );
    }

}

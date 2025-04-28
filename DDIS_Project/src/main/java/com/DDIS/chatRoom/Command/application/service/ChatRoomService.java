package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RoomRepository roomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, RoomRepository roomRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.roomRepository = roomRepository;
    }

    // 채팅방 생성
//    public ChatRoomResponseDTO createRoom(ChatRoomRequestDTO dto) {
//        String now = LocalDateTime.now().format(FORMATTER);
//        Rooms room = roomRepository.findById(dto.getRoom_num())
//                .orElseThrow(() -> new IllegalArgumentException("방 정보 없음"));
//
//        ChatRoomEntity chatRoom = new ChatRoomEntity();
//        chatRoom.setChatRoomName(dto.getChatroom_name());
//        chatRoom.setChatType(dto.getChatroom_type());
//        chatRoom.setRoomNum(room);
//        chatRoom.setCreatedTime(now);
//
//        ChatRoomEntity saved = chatRoomRepository.save(chatRoom);
//
//        return new ChatRoomResponseDTO(
//                saved.getChatRoomId(),
//                saved.getChatRoomName(),
//                saved.getChatType(),
//                saved.getRoomNum().getRoomNum(),
//                saved.getCreatedTime()
//        );
//    }

    public void createChatRoom(Rooms savedRoom) {

        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setRoomNum(savedRoom.getRoomNum());
        chatRoom.setChatRoomName(savedRoom.getTitle());
//        chatRoom.setChatType(savedRoom.get());
        chatRoom.setRooms(savedRoom); // savedRoom은 Room 저장 결과
        chatRoom.setCreatedTime(savedRoom.getStartDate());

        chatRoomRepository.save(chatRoom);
    }
}

package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public List<ChatRoomEntity> getAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoomEntity getRoom(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    public ChatRoomEntity createRoom(String name) {
        return chatRoomRepository.createRoom(name);
    }
}

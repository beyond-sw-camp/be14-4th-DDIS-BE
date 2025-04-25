package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {
    private final Map<String, ChatRoomEntity> chatRoomMap = new LinkedHashMap<>();

    public List<ChatRoomEntity> findAll() {
        return new ArrayList<>(chatRoomMap.values());
    }

    public ChatRoomEntity findById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    public ChatRoomEntity createRoom(String name) {
        ChatRoomEntity room = new ChatRoomEntity(name);
        chatRoomMap.put(room.getRoomId(), room);
        return room;
    }
}

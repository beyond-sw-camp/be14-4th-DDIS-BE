package com.DDIS.chatRoom.Query.service;

import com.DDIS.chatRoom.Query.dao.ChatRoomMapper;
import com.DDIS.chatRoom.Query.dto.ChatRoomQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomQueryService {

    private final ChatRoomMapper chatRoomMapper;

    public List<ChatRoomQueryDTO> getAllRooms() { return chatRoomMapper.findAll();    }

    public ChatRoomQueryDTO getRoomById(Long chatroomNum) {
        return chatRoomMapper.findById(chatroomNum);
    }
}

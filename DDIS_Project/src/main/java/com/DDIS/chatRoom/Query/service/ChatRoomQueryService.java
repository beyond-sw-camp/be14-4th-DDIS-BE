package com.DDIS.chatRoom.Query.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Query.dao.ChatRoomQueryMapper;
import com.DDIS.chatRoom.Query.dto.ChatRoomQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomQueryService {

    private final ChatRoomQueryMapper chatRoomQueryMapper;

    public List<ChatRoomQueryDTO> getAllRooms() { return chatRoomQueryMapper.findAll();    }

    public ChatRoomQueryDTO getRoomById(Long chatroomNum) {
        return chatRoomQueryMapper.findById(chatroomNum);
    }
}

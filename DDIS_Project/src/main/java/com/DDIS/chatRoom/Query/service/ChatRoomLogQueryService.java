package com.DDIS.chatRoom.Query.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomResponseDTO;
import com.DDIS.chatRoom.Query.dao.ChatRoomLogQueryMapper;
import com.DDIS.chatRoom.Query.dao.ChatRoomQueryMapper;
import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomLogQueryService {

    private final ChatRoomLogQueryMapper chatRoomLogQueryMapper;


    public List<ChatRoomLogQueryDTO> getMessagesByRoomAsDTO(Long roomNum) {
        return chatRoomLogQueryMapper.findByid(roomNum);
    }
}

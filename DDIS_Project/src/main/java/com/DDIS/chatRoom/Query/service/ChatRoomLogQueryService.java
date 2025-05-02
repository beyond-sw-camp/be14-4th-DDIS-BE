package com.DDIS.chatRoom.Query.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.chatRoom.Query.dao.ChatRoomLogMapper;
import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomLogQueryService {

    private final ChatRoomLogMapper chatRoomLogMapper;


    public List<ChatRoomLogQueryDTO> getMessagesByRoomAsDTO(Long roomNum) {
        return chatRoomLogMapper.findById(roomNum);
    }

    public List<ChatRoomLogQueryDTO> getLogsByRoomNum(Long roomNum) {
        return chatRoomLogMapper.findLogsByRoomNum(roomNum);
    }
}

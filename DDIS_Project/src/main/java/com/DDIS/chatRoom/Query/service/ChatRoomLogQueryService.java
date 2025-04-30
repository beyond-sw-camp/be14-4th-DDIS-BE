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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomLogQueryService {

    private final ChatRoomLogMapper chatRoomLogMapper;


    public List<ChatRoomLogQueryDTO> getMessagesByRoomAsDTO(Long roomNum) {
        return chatRoomLogMapper.findById(roomNum);
    }

    public List<ChatRoomLogQueryDTO> getLogsByRoomNum(Long roomNum) {
//        ChatRoomLogQueryDTO chatRoom = chatRoomLogMapper.findLogsByRoomNum(roomNum)
//                .orElseThrow(() -> new IllegalArgumentException("해당 공동 방의 채팅방이 없습니다."));

//        List<ChatRoomLogEntity> logs = chatRoomLogMapper.findByChatRoomNum(chatRoom);
//
//        return logs.stream().map(log -> new ChatRoomLogQueryDTO(
//                log.getMessageNum(),
//                log.getChatRoomNum().getChatRoomNum(),
//                log.getSender(),
//                log.getMessage(),
//                log.getSendTime()
//        )).collect(Collectors.toList());
        return chatRoomLogMapper.findLogsByRoomNum(roomNum);
    }
}

package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomLogRepository;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ChatRoomLogService {

    private final ChatRoomLogRepository chatRoomLogRepository;
    private final ChatRoomRepository chatRoomRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ChatRoomLogService(ChatRoomLogRepository chatRoomLogRepository,ChatRoomRepository chatRoomRepository) {
        this.chatRoomLogRepository = chatRoomLogRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public void saveMessage(ChatRoomLogRequestDTO requestDTO) {
        System.out.println("💬 saveMessage 호출: " + requestDTO);

        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(requestDTO.getChatRoomNum())
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        String formattedTime = requestDTO.getSendTime().format(FORMATTER);

        ChatRoomLogEntity entity = ChatRoomLogEntity.builder()
                .chatRoomNum(chatRoomEntity)
                .sender(requestDTO.getSender())
                .message(requestDTO.getMessage())
                .sendTime(formattedTime)
                .build();

        chatRoomLogRepository.save(entity);
    }


    public void deleteMessage(Long logId) {
        chatRoomLogRepository.deleteById(logId);
    }

}

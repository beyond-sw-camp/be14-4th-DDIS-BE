package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomLogRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ChatRoomLogService {

    private final ChatRoomLogRepository chatRoomLogRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ChatRoomLogService(ChatRoomLogRepository chatRoomLogRepository) {
        this.chatRoomLogRepository = chatRoomLogRepository;
    }

    public ChatRoomLogResponseDTO saveMessage(ChatRoomLogRequestDTO requestDTO) {

        String formattedTime = requestDTO.getSendTime().format(String.valueOf(FORMATTER));

        // DB 저장
        ChatRoomLogEntity entity = new ChatRoomLogEntity(
                requestDTO.getRoomNum(),
                requestDTO.getSender(),
                requestDTO.getMessage(),
                formattedTime
        );
        ChatRoomLogEntity saved = chatRoomLogRepository.save(entity);

        return new ChatRoomLogResponseDTO(
                saved.getRoomNum(),
                saved.getSender(),
                saved.getMessage(),
                saved.getSendTime()
        );
    }

    // 메시지 리스트 조회 (DTO 변환)
    public List<ChatRoomLogResponseDTO> getMessagesByRoomAsDTO(ChatRoomEntity roomNum) {
        return chatRoomLogRepository.findByRoomNumOrderBySendTimeAsc(roomNum).stream()
                .map(log -> new ChatRoomLogResponseDTO(
                        log.getRoomNum(),
                        log.getSender(),
                        log.getMessage(),
                        log.getSendTime()
                ))
                .toList();
    }
}

package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ChatRoomLogService {

    private final ChatRoomLogRepository chatRoomLogRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ChatRoomLogService(ChatRoomLogRepository chatRoomLogRepository) {
        this.chatRoomLogRepository = chatRoomLogRepository;
    }

    public ChatRoomLogResponseDTO saveMessage(ChatRoomLogRequestDTO requestDTO) {
        System.out.println("💬 saveMessage 호출: " + requestDTO);


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


    public void deleteMessage(Long logId) {
        chatRoomLogRepository.deleteById(logId);
    }

    public void deleteMessagesByRoom(ChatRoomEntity roomNum) {
        chatRoomLogRepository.deleteByRoomNum(roomNum);
    }
}

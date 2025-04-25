package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.application.dto.ChatMessageDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatMessageEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageDTO processAndSave(ChatMessageDTO dto) {
        if (dto.getType() == ChatMessageDTO.MessageType.ENTER) {
            dto.setMessage(dto.getSender() + "님이 입장하셨습니다.");
        } else if (dto.getType() == ChatMessageDTO.MessageType.LEAVE) {
            dto.setMessage(dto.getSender() + "님이 퇴장하셨습니다.");
        }

        // DB 저장
        ChatMessageEntity entity = new ChatMessageEntity(
                dto.getRoomId(),
                dto.getSender(),
                dto.getMessage()
        );
        chatMessageRepository.save(entity);

        return dto;
    }

    // 메시지 리스트 조회
    public List<ChatMessageEntity> getMessagesByRoom(String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }
}

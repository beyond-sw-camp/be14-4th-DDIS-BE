package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogRequestDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomLogResponseDTO;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomLogRepository;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import com.DDIS.shareTodo.Command.domain.repository.MembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ChatRoomLogService {

    private final ChatRoomLogRepository chatRoomLogRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MembersRepository membersRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ChatRoomLogService(ChatRoomLogRepository chatRoomLogRepository, ChatRoomRepository chatRoomRepository, MembersRepository membersRepository) {
        this.chatRoomLogRepository = chatRoomLogRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.membersRepository = membersRepository;
    }

    @Transactional
    public void saveMessage(ChatRoomLogRequestDTO dto) {
        System.out.println("🔥 저장 시도: " + dto);

        ChatRoomEntity chatRoom = chatRoomRepository.findById(dto.getChatRoomNum())
                .orElseThrow(() -> new IllegalArgumentException("채팅방 없음"));

        ChatRoomLogEntity entity = ChatRoomLogEntity.builder()
                .chatRoomNum(chatRoom)
                .sender(dto.getSender())
                .senderName(dto.getSenderName())
                .message(dto.getMessage())
                .sendTime(dto.getSendTime().toString())
                .build();

        chatRoomLogRepository.save(entity);
        System.out.println("✅ 저장 완료!");
    }




    public void deleteMessage(Long logId) {
        chatRoomLogRepository.deleteById(logId);
    }

}

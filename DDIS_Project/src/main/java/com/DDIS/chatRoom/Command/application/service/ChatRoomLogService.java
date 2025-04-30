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

//    public void saveMessage(ChatRoomLogRequestDTO requestDTO) {
//        System.out.println("💬 saveMessage 호출: " + requestDTO);
//
//        ChatRoomEntity chatRoomEntity = chatRoomRepository.findById(requestDTO.getChatRoomNum())
//                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));
//
//        String formattedTime = requestDTO.getSendTime().format(String.valueOf(FORMATTER));
//
//        ChatRoomLogEntity entity = ChatRoomLogEntity.builder()
//                .chatRoomNum(chatRoomEntity)
//                .sender(requestDTO.getSender())
//                .message(requestDTO.getMessage())
//                .sendTime(formattedTime)
//                .build();
//
//        chatRoomLogRepository.save(entity);
//    }

    // ChatRoomLogService.java

    public void saveMessage(ChatRoomLogRequestDTO dto) {
        // 🔍 1. sender가 해당 room에 속한지 확인
//        Members member = membersRepository.findByRoom_RoomNumAndClient_ClientNum(dto.getChatRoomNum(), dto.getSender())
//                .orElseThrow(() -> new IllegalArgumentException("채팅방에 속하지 않은 사용자입니다."));

        ChatRoomEntity chatRoom = chatRoomRepository.findById(dto.getChatRoomNum())
                .orElseThrow(() -> new RuntimeException("채팅방 없음"));

        // ✅ 2. 시간 포맷 처리
        String formattedTime = dto.getSendTime().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // ✅ 3. 로그 저장
        ChatRoomLogEntity log = ChatRoomLogEntity.builder()
//                .chatRoomNum(chatRoomRepository.findById(dto.getChatRoomNum()).orElseThrow())
                .chatRoomNum(chatRoom)
                .sender(dto.getSender())
                .message(dto.getMessage())
                .sendTime(formattedTime)
                .build();

        chatRoomLogRepository.save(log);
    }



    public void deleteMessage(Long logId) {
        chatRoomLogRepository.deleteById(logId);
    }

}

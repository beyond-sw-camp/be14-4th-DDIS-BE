package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomAccessService {
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomAccessService(ChatRoomUserRepository chatRoomUserRepository, ChatRoomRepository chatRoomRepository) {
        this.chatRoomUserRepository = chatRoomUserRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean canAccessChatRoom(Long chatRoomNum, Long clientNum) {
        // 채팅방 존재 여부 확인
        if (!chatRoomRepository.existsById(chatRoomNum)) {
            throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");
        }

        // ChatRoomUser 테이블에서 접근 가능 여부 확인
        return chatRoomUserRepository.existsByChatRoom_ChatRoomNumAndClientNum(chatRoomNum, clientNum);
    }

}

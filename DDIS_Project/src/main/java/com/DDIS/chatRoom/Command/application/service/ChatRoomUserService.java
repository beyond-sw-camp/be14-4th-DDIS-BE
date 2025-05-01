package com.DDIS.chatRoom.Command.application.service;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomUserEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatRoomUserService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;

    public ChatRoomUserService(ChatRoomRepository chatRoomRepository, ChatRoomUserRepository chatRoomUserRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatRoomUserRepository = chatRoomUserRepository;
    }

    // ✅ 채팅방 입장
    @Transactional
    public void enterChatRoom(Long chatRoomNum, Long clientNum, String role) {
        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatRoomNum)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        // 중복 입장 방지
        chatRoomUserRepository.findByChatRoom_ChatRoomNumAndClientNum(chatRoomNum, clientNum)
                .ifPresent(user -> { throw new IllegalStateException("이미 입장한 사용자입니다."); });

        ChatRoomUserEntity user = ChatRoomUserEntity.builder()
                .chatRoom(chatRoom)
                .clientNum(clientNum)
                .role(role)
                .build();

        chatRoomUserRepository.save(user);
    }

    @Transactional
    public void updateLastReadMessage(Long chatRoomNum, Long clientNum, Long lastMsgNum) {
        ChatRoomUserEntity user = chatRoomUserRepository.findByChatRoom_ChatRoomNumAndClientNum(chatRoomNum, clientNum)
                .orElseThrow(() -> new IllegalArgumentException("채팅방 사용자 정보가 없습니다."));
        user.setLastMsgNum(lastMsgNum);
        chatRoomUserRepository.save(user);
    }


    // ✅ 채팅방 퇴장
    @Transactional
    public void leaveChatRoom(Long chatRoomNum, Long clientNum) {
        chatRoomUserRepository.deleteByChatRoom_ChatRoomNumAndClientNum(chatRoomNum, clientNum);
    }
}

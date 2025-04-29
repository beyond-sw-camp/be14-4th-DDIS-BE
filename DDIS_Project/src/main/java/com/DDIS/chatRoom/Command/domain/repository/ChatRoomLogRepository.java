package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomLogRepository extends JpaRepository<ChatRoomLogEntity, Long> {

    // 특정 채팅방의 로그를 시간 순으로 가져오기
    List<ChatRoomLogEntity> findByChatRoomNumOrderBySendTimeAsc(ChatRoomEntity roomNum);

    void deleteByChatRoomNum(ChatRoomEntity chatRoomNum);

    // 특정 방 + 특정 유저의 메시지만 가져오기
//    List<ChatRoomLogEntity> findByRoomIdAndSenderOrderBySendTimeAsc(String roomId, String sender);
}
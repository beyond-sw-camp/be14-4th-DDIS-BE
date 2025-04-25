package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    List<ChatMessageEntity> findByRoomIdOrderByTimestampAsc(String roomId);
}
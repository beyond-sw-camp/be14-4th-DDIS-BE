package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    // 필요 시 room_num으로 조회하는 메서드도 만들 수 있음
//    Optional<ChatRoomEntity> findByRoomNum(Long roomNum);
}
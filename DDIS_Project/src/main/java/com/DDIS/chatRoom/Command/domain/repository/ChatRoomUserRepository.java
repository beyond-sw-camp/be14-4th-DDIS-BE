package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUserEntity, ChatRoomUserEntity.PK> {

    Optional<ChatRoomUserEntity> findByChatRoom_ChatRoomNumAndClientNum(Long roomNum, Long clientNum);

    void deleteByChatRoom_ChatRoomNumAndClientNum(Long chatRoomId, Long clientNum);

}
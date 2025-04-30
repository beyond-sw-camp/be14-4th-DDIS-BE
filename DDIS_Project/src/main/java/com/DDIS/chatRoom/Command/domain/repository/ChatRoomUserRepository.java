package com.DDIS.chatRoom.Command.domain.repository;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUserEntity, ChatRoomUserEntity.PK> {

    Optional<ChatRoomUserEntity> findByChatRoom_ChatRoomNumAndClientNum(Long roomNum, Long clientNum);

    void deleteByChatRoom_ChatRoomNumAndClientNum(Long chatRoomId, Long clientNum);

    // 채팅방 목록 조회 (사용자 기준)
    boolean existsByChatRoom_ChatRoomNumAndClientNum(Long chatRoomNum, Long clientNum);

    @Query("SELECT cu.chatRoom FROM ChatRoomUserEntity cu WHERE cu.clientNum = :clientNum")
    List<ChatRoomEntity> findChatRoomsByClientNum(@Param("clientNum") Long clientNum);
}
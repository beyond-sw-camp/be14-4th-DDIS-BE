package com.DDIS.approve.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {
    List<Members> findByRoom_RoomNum(Long roomNum);

    List<Members> findByClient_ClientNum(Long clientNum);

    @Query("SELECT m.memberNum FROM Members m WHERE m.room.roomNum = :roomNum AND m.client.clientNum = :clientNum")
    Long findMemberNumByRoomNumAndClientNum(@Param("roomNum") Long roomNum, @Param("clientNum") Long clientNum);
}

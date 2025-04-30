package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long >{

    @Query("SELECT r FROM Rooms r JOIN Members m ON r.roomNum = m.room.roomNum WHERE m.client.clientNum = :memberId")
    List<Rooms> findRoomsByMemberId(@Param("memberId") Long memberId);
}

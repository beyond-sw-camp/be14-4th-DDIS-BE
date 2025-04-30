package com.DDIS.chatRoom.Query.dao;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomLogEntity;
import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChatRoomLogMapper {
    List<ChatRoomLogQueryDTO> findById(Long roomNum);

    List<ChatRoomLogEntity> findByChatRoomNum(ChatRoomLogQueryDTO chatRoom);
//    Optional<ChatRoomEntity> findByRoomNum(Long roomNum);

//    List<ChatRoomLogQueryDTO> findLogsByRoomNum(@Param("roomNum") Long roomNum);
    List<ChatRoomLogQueryDTO> findLogsByRoomNum(Long roomNum);
}

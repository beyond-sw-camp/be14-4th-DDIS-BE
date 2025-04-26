package com.DDIS.chatRoom.Query.dao;

import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomLogMapper {
    List<ChatRoomLogQueryDTO> findById(Long roomNum);
}

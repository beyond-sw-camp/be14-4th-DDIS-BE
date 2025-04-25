package com.DDIS.chatRoom.Query.dao;

import com.DDIS.chatRoom.Query.dto.ChatRoomLogQueryDTO;
import com.DDIS.chatRoom.Query.dto.ChatRoomQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomQueryMapper {
    List<ChatRoomQueryDTO> findAll();

    ChatRoomQueryDTO findById(Long chatroomNum);
}

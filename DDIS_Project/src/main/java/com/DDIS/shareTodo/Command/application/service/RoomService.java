package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.*;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomService {
    Rooms createRoom(CreateShareRoomDTO roomDTO);
    List<ShareTodoResponseDTO> saveShareTodos(List<SaveShareTodoDTO> todoList);
    // RoomService.java
    Rooms findRoomByRoomNum(Long roomNum);
    List<MemberShareTodoResponseDTO> generateAndSaveGptTodos(Long roomNum, String topic);

    List<ResponseRoomDTO> getRoomsByClientNum(Long clientNum);

    RoomDetailDTO getRoomDataByRoomNum(Long roomNum, Long memberNum);

    void updateApproveRequiredCount(Long roomNum, Integer count);
}

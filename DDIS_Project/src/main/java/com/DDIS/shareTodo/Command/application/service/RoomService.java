package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.dto.MemberShareTodoResponseDTO;
import com.DDIS.shareTodo.Command.application.dto.SaveShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomService {
    Rooms createRoom(CreateShareRoomDTO roomDTO);
    void saveShareTodos(List<SaveShareTodoDTO> todoList);
    // RoomService.java
    Rooms findRoomByRoomNum(Long roomNum);
    List<MemberShareTodoResponseDTO> generateAndSaveGptTodos(Long roomNum, String topic);

}

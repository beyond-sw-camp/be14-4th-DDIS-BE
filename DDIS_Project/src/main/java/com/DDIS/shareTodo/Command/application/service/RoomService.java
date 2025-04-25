package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import org.springframework.stereotype.Service;


public interface RoomService {
    void createRoom(CreateShareRoomDTO roomDTO);
}

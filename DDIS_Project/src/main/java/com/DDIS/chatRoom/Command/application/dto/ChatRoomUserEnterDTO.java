package com.DDIS.chatRoom.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomUserEnterDTO {
    private Long chatroomNum;
    private Long clientNum;
    private String role;
}
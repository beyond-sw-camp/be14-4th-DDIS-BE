package com.DDIS.chatRoom.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomUserLeaveDTO {
    private Long chatroomNum;
    private Long clientNum;
}

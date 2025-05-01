package com.DDIS.chatRoom.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomReadRequestDTO {
    private Long roomId;
    private Long clientId;
    private Long lastReadMessageId;
}
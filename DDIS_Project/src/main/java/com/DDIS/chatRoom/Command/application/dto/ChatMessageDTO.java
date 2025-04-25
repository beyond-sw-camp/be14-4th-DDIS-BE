package com.DDIS.chatRoom.Command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageDTO {
    public enum MessageType {
        ENTER, TALK, LEAVE
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}

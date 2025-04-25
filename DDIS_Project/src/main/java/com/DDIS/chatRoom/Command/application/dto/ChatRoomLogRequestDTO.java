package com.DDIS.chatRoom.Command.application.dto;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomLogRequestDTO {

//    public enum MessageType {
//        ENTER, TALK, LEAVE
//    }
//
//    private MessageType type;
    private ChatRoomEntity roomNum;
    private String sender;
    private String message;
    private String sendTime;
}

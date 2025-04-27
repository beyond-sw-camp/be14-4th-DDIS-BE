package com.DDIS.chatRoom.Command.application.dto;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomLogRequestDTO {
    private ChatRoomEntity roomNum;
    private Long sender;
    private String message;
    private String sendTime;
}

package com.DDIS.chatRoom.Command.application.dto;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomLogRequestDTO {
    private Long chatRoomNum;
    private Long sender;
    private String message;
    private String sendTime;

}

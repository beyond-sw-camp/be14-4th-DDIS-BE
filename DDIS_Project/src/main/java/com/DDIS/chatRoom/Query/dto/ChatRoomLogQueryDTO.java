package com.DDIS.chatRoom.Query.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomLogQueryDTO {
    private Long messageNum;
    private Long chatRoomNum;
    private Long clientNum;
    private String clientName;
    private String messageContent;
    private String sendTime;
}
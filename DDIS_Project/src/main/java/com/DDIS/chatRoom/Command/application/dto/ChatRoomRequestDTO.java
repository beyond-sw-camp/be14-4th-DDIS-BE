package com.DDIS.chatRoom.Command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomRequestDTO {
    private String chatRoomName;
    private String chatRoomType;
    private Long chatRoomNum;

}
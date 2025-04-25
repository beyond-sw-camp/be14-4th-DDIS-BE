package com.DDIS.chatRoom.Command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomRequestDTO {
    private String chatroom_name;
    private String chatroom_type;
    private Long room_num;

}
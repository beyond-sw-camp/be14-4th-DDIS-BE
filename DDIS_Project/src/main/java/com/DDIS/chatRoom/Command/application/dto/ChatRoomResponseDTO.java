package com.DDIS.chatRoom.Command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomResponseDTO {
    private Long chatroom_num;
    private String chatroom_name;
    private String chatroom_type;
    private Long room_num;
    private String created_time;
}

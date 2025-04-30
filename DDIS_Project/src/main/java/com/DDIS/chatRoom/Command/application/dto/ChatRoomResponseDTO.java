package com.DDIS.chatRoom.Command.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomResponseDTO {
    private Long chatRoomNum;
    private String chatRoomName;
    private String chatRoomType;
    private Long roomNum;
    private String createdTime;
}

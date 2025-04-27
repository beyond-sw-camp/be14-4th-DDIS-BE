package com.DDIS.chatRoom.Query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomQueryDTO {
    private Long chatRoomNum;
    private String chatRoomName;
    private String chatRoomType;
    private Long roomNum;
    private String createdTime;
}
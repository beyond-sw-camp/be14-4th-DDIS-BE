package com.DDIS.chatRoom.Command.application.dto;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomSimpleDTO {
    private Long chatRoomNum;
    private String chatRoomName;
    private String chatRoomType;
    private String createdTime;

    public static ChatRoomSimpleDTO from(ChatRoomEntity entity) {
        return ChatRoomSimpleDTO.builder()
                .chatRoomNum(entity.getChatRoomNum())
                .chatRoomName(entity.getChatRoomName())
                .chatRoomType(entity.getChatRoomType())
                .createdTime(entity.getCreatedTime())
                .build();
    }
}
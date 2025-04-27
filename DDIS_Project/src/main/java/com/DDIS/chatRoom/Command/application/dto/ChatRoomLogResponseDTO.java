package com.DDIS.chatRoom.Command.application.dto;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomLogResponseDTO {

    private ChatRoomEntity roomNum;         // 채팅방 ID
    private Long sender;         // 보낸 사람
    private String message;        // 메시지 본문
    private String sendTime; // 서버에서 저장된 시간
}
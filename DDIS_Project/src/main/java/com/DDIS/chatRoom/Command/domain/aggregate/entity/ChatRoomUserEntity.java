package com.DDIS.chatRoom.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "chatroom_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ChatRoomUserEntity.PK.class)  // ✅ 내부 static class PK 사용
public class ChatRoomUserEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_num")
    private ChatRoomEntity chatRoom; // ✅ 채팅방 (FK)

    @Id
    @Column(name = "client_num")
    private Long clientNum;           // ✅ 사용자 ID (FK)

    private String role;              // 역할 (관리자/일반)

    @Column(name = "last_read_message_num")
    private Long lastMsgNum; // 마지막 읽은 메시지 번호 (nullable)

    // ✅ 내부 static class로 복합키 정의
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {
        private Long chatRoom;
        private Long clientNum;
    }
}

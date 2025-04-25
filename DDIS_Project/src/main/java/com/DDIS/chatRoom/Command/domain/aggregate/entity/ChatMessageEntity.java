package com.DDIS.chatRoom.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_num")
    private ChatRoomEntity chatRoomEntity;
    private String roomId;

    private String sender;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime timestamp;

//    public ChatMessageEntity() {}

    public ChatMessageEntity(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getter & Setter
}
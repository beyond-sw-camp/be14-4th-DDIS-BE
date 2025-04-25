package com.DDIS.chatRoom.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "chatroom_log")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "chatroom_num")
    private ChatRoomEntity roomNum;
//    private String roomId;

    @Column(name = "client_num")
    private String sender;

    @Column(name = "message_content")
    private String message;

    private String sendTime;

    public ChatRoomLogEntity(ChatRoomEntity roomNum, String sender, String message, String sendTime) {
        this.roomNum = roomNum;
        this.sender = sender;
        this.message = message;
        this.sendTime = sendTime;
    }
}
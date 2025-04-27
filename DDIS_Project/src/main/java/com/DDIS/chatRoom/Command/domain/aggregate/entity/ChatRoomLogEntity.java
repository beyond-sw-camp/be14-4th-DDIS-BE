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
    @Column(name = "message_num")
    private Long messageNum;

    @ManyToOne
    @JoinColumn(name = "chatroom_num")
    private ChatRoomEntity roomNum;
//    private String roomId;

    @Column(name = "client_num")
    private Long sender;

    @Column(name = "message_content")
    private String message;

    @Column(name = "send_time")
    private String sendTime;

    public ChatRoomLogEntity(ChatRoomEntity roomNum, Long sender, String message, String sendTime) {
        this.roomNum = roomNum;
        this.sender = sender;
        this.message = message;
        this.sendTime = sendTime;
    }
}
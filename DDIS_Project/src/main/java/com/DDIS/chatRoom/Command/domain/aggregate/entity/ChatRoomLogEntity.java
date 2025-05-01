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
@Builder
public class ChatRoomLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_num")
    private Long messageNum;

    @ManyToOne
    @JoinColumn(name = "chatroom_num")
    private ChatRoomEntity chatRoomNum;

    @Column(name = "client_num")
    private Long sender;

    @Column(name = "client_nickname")
    private String senderName;

    @Column(name = "message_content")
    private String message;

    @Column(name = "send_time")
    private String sendTime;

//    public ChatRoomLogEntity(ChatRoomEntity chatRoomNum, Long sender, String message, String sendTime) {
//        this.chatRoomNum = chatRoomNum;
//        this.sender = sender;
//        this.message = message;
//        this.sendTime = sendTime;
//    }
}
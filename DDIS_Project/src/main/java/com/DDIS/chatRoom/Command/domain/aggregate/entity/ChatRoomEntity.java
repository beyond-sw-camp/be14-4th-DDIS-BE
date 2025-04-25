package com.DDIS.chatRoom.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "chatroom")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

//    @ManyToOne
//    @JoinColumn(name = "room_num")
    @Column(name = "room_num")
//    private Rooms roomNum;
    private int roomNum;

    @Column(name = "chatroom_name")
    private String name;

    @Column(name = "chatroom_type")
    private String type;

    @Column(name = "created_time")
    private String time;

//    public ChatRoomEntity() {
//        this.roomId = UUID.randomUUID().toString();
//    }
//
    public ChatRoomEntity(String name) {
        this();
        this.name = name;
    }

    // Getter & Setter
}
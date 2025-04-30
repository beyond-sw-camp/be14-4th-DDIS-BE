package com.DDIS.chatRoom.Command.domain.aggregate.entity;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "chatroom_num")
    private Long chatRoomNum;

    @ManyToOne
    @JoinColumn(name = "room_num")
    private Rooms roomNum;

    @Column(name = "chatroom_name")
    private String chatRoomName;

    @Column(name = "chatroom_type")
    private String chatRoomType;

    @Column(name = "created_time")
    private String createdTime;

    public ChatRoomEntity(String name) {
        this();
        this.chatRoomName = name;
    }
}
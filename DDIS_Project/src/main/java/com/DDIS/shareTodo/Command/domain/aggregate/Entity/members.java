package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "members")
public class members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_num")
    private int memberNum;

    @ManyToOne
    @JoinColumn(name = "room_num")
    private Rooms room;

    @ManyToOne
    @JoinColumn(name = "post_num")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "client_num")
    private Clients client;
}

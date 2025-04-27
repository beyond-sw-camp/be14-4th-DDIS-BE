package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
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
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_num")
    private Long memberNum;

    @ManyToOne
    @JoinColumn(name = "room_num")
    private Rooms room;

    @ManyToOne
    @JoinColumn(name = "post_num")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "client_num")
    private Clients client;
}

package com.DDIS.follow.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "follows")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_num")
    private Long followNum;

    @Column(name = "follower_num")
    private Long followerNum;

    @Column(name = "following_num")
    private Long followingNum;
}

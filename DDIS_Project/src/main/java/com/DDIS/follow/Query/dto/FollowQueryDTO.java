package com.DDIS.follow.Query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FollowQueryDTO {

    private Long followNum;
    private Long followerNum;
    private Long followingNum;

    private String clientNickname;
}

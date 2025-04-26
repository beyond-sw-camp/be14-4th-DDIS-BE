package com.DDIS.follow.Command.application.service;

import org.springframework.stereotype.Service;

public interface FollowCommandService {

    void follow(Long followerNum, Long followingNum);
    void unfollow(Long followerNum, Long followingNum);
}

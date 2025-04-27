package com.DDIS.follow.Command.application.service;

import com.DDIS.follow.Command.domain.aggregate.FollowEntity;
import com.DDIS.follow.Command.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowCommandServiceImpl implements FollowCommandService {

    private final FollowRepository followRepository;

    @Transactional
    public void follow(Long followerNum, Long followingNum) {
        if (!followRepository.existsByFollowerNumAndFollowingNum(followerNum, followingNum)) {
            FollowEntity follow = FollowEntity.builder()
                    .followerNum(followerNum)
                    .followingNum(followingNum)
                    .build();
            followRepository.save(follow);
        }
    }

    @Transactional
    public void unfollow(Long followerNum, Long followingNum) {
        followRepository.deleteByFollowerNumAndFollowingNum(followerNum, followingNum);
    }
}

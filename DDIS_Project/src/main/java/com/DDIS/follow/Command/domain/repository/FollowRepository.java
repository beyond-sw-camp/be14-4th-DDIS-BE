package com.DDIS.follow.Command.domain.repository;

import com.DDIS.follow.Command.domain.aggregate.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    void deleteByFollowerNumAndFollowingNum(Long followerNum, Long followingNum);
    boolean existsByFollowerNumAndFollowingNum(Long followerNum, Long followingNum);
}

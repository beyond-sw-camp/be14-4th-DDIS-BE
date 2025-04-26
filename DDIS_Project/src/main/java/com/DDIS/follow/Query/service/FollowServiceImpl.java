package com.DDIS.follow.Query.service;

import com.DDIS.follow.Query.dto.FollowQueryDTO;
import com.DDIS.follow.Query.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowMapper followMapper;

    @Override
    public List<FollowQueryDTO> getAllFollowers(Long clientNum) {
        return followMapper.findAllFollowersByFollowerNum(clientNum);
    }

    @Override
    public List<FollowQueryDTO> getAllFollowings(Long clientNum) {
        return followMapper.findAllFollowingsByFollowingNum(clientNum);
    }
}

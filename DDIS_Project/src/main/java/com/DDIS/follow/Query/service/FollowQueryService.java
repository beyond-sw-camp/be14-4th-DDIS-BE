package com.DDIS.follow.Query.service;

import com.DDIS.follow.Query.dto.FollowQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowQueryService {

    List<FollowQueryDTO> getAllFollowers(Long clientNum);

    List<FollowQueryDTO> getAllFollowings(Long clientNum);

    public Long findClientNumByClientId(String clientId);

}

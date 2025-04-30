package com.DDIS.follow.Query.service;

import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.follow.Query.dto.FollowQueryDTO;
import com.DDIS.follow.Query.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowQueryServiceImpl implements FollowQueryService {

    private final FollowMapper followMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<FollowQueryDTO> getAllFollowers(Long clientNum) {
        return followMapper.findAllFollowersByFollowerNum(clientNum);
    }

    @Override
    public List<FollowQueryDTO> getAllFollowings(Long clientNum) {
        return followMapper.findAllFollowingsByFollowingNum(clientNum);
    }

    @Override
    public Long findClientNumByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"))
                .getClientNum();
    }

}

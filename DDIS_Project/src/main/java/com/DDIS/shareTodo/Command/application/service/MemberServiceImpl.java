package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.approve.Command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public Long getMemberNum(Long roomNum, Long clientNum) {
        return memberRepository.findMemberNumByRoomNumAndClientNum(roomNum, clientNum);
    }
}

package com.DDIS.follow.Command.application.controller;

import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.follow.Command.application.dto.FollowCommandDTO;
import com.DDIS.follow.Command.application.service.FollowCommandService;
import com.DDIS.follow.Command.domain.repository.FollowRepository;
import com.DDIS.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowCommandController {

    private final FollowCommandService followCommandService;
    private final JwtUtil jwtUtil;
    private final ClientRepository clientRepository;

    @PostMapping("/follow/{followingNum}")
    public ResponseEntity<Void> follow(@PathVariable Long followingNum,
                                       @RequestHeader("Authorization") String authorizationHeader) {
        // 1. 토큰에서 clientId 추출
        String token = authorizationHeader.replace("Bearer ", "");
        String clientId = jwtUtil.getClientId(token);  // JwtUtil 사용

        // 2. clientId → clientNum
        Long followerNum = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("회원 없음"))
                .getClientNum();

        // 3. 팔로우 처리
        followCommandService.follow(followerNum, followingNum);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{followerNum}/{followingNum}")
    public ResponseEntity<Void> unfollow(@PathVariable Long followerNum, @PathVariable Long followingNum) {
        followCommandService.unfollow(followerNum, followingNum);
        return ResponseEntity.ok().build();
    }
}

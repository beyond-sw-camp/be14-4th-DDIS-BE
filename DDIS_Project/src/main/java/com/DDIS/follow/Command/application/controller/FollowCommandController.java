package com.DDIS.follow.Command.application.controller;

import com.DDIS.follow.Command.application.dto.FollowCommandDTO;
import com.DDIS.follow.Command.application.service.FollowCommandService;
import com.DDIS.follow.Command.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowCommandController {

    private final FollowCommandService followCommandService;

    @PostMapping("follow")
    public ResponseEntity<Void> follow(@RequestBody FollowCommandDTO request) {
        followCommandService.follow(request.getFollowerNum(), request.getFollowingNum());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{followerNum}/{followingNum}")
    public ResponseEntity<Void> unfollow(@PathVariable Long followerNum, @PathVariable Long followingNum) {
        followCommandService.unfollow(followerNum, followingNum);
        return ResponseEntity.ok().build();
    }
}

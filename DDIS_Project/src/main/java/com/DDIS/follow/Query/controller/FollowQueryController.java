package com.DDIS.follow.Query.controller;

import com.DDIS.follow.Query.dto.FollowQueryDTO;
import com.DDIS.follow.Query.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("follows")
@RequiredArgsConstructor
public class FollowQueryController {

    private final FollowService followService;

    @GetMapping("/{clientNum}/followers")
    public ResponseEntity<List<FollowQueryDTO>> getAllFollowers(@PathVariable Long clientNum) {
        List<FollowQueryDTO> followers = followService.getAllFollowers(clientNum);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{clientNum}/followings")
    public ResponseEntity<List<FollowQueryDTO>> getAllFollowings(@PathVariable Long clientNum) {
        List<FollowQueryDTO> followings = followService.getAllFollowings(clientNum);
        return ResponseEntity.ok(followings);
    }
}

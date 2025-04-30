package com.DDIS.follow.Query.controller;

import com.DDIS.follow.Query.dto.FollowQueryDTO;
import com.DDIS.follow.Query.service.FollowQueryService;
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

    private final FollowQueryService followQueryService;

    @GetMapping("/{clientId}/followers")
    public ResponseEntity<List<FollowQueryDTO>> getAllFollowers(@PathVariable String clientId) {
        Long clientNum = followQueryService.findClientNumByClientId(clientId);
        List<FollowQueryDTO> followers = followQueryService.getAllFollowers(clientNum);
        return ResponseEntity.ok(followers);
    }


    @GetMapping("/{clientId}/followings")
    public ResponseEntity<List<FollowQueryDTO>> getAllFollowings(@PathVariable String clientId) {
        Long clientNum = followQueryService.findClientNumByClientId(clientId);
        List<FollowQueryDTO> followings = followQueryService.getAllFollowings(clientNum);
        return ResponseEntity.ok(followings);
    }

}

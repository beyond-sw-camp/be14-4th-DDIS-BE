package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.shareTodo.Command.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/find")
    public ResponseEntity<Map<String, Long>> findMemberNum(
            @RequestParam Long roomNum,
            @RequestParam Long clientNum) {

        Long memberNum = memberService.getMemberNum(roomNum, clientNum);
        Map<String, Long> result = new HashMap<>();
        result.put("memberNum", memberNum);
        return ResponseEntity.ok(result);
    }
}

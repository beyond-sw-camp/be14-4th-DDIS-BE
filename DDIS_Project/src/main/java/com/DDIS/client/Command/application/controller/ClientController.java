package com.DDIS.client.Command.application.controller;

import com.DDIS.client.Command.application.service.ClientService;
import com.DDIS.client.Command.domain.vo.LoginRequestVO;
import com.DDIS.client.Command.domain.vo.LoginResponseVO;
import com.DDIS.client.Command.domain.vo.SignupRequestVO;
import com.DDIS.client.Command.domain.vo.SignupResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseVO> signup(@RequestBody SignupRequestVO request) {
        SignupResponseVO response = clientService.signup(request);

        if ("이미 존재하는 아이디입니다.".equals(response.getMessage())) {
            return ResponseEntity.badRequest().body(response); // 400
        }

        return ResponseEntity.status(201).body(response); // 201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseVO> login(@RequestBody LoginRequestVO vo) {
        LoginResponseVO response = clientService.login(vo);

        if (response.getToken() == null) {
            return ResponseEntity.status(401).body(response); // 인증 실패
        }

        return ResponseEntity.ok(response); // 인증 성공
    }
}
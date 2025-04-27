package com.DDIS.client.Command.application.controller;

import com.DDIS.client.Command.application.service.ClientService;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.client.Command.domain.vo.LoginRequestVO;
import com.DDIS.client.Command.domain.vo.LoginResponseVO;
import com.DDIS.client.Command.domain.vo.SignupRequestVO;
import com.DDIS.client.Command.domain.vo.SignupResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseVO> signup(@RequestBody SignupRequestVO vo) {
        SignupResponseVO response = clientService.signup(vo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseVO> login(@RequestBody LoginRequestVO vo) {
        LoginResponseVO response = clientService.login(vo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-id")
    public ResponseEntity<String> checkClientId(@RequestParam String clientId) {
        boolean exists = clientRepository.findByClientId(clientId).isPresent();
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }
}
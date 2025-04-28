package com.DDIS.client.Command.application.controller;

import com.DDIS.client.Command.application.service.ClientService;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.client.Command.domain.vo.*;
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

    // 회원 가입 API
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseVO> signup(@RequestBody SignupRequestVO vo) {
        SignupResponseVO response = clientService.signup(vo);
        return ResponseEntity.ok(response);
    }

    // 회원 Login API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseVO> login(@RequestBody LoginRequestVO vo) {
        LoginResponseVO response = clientService.login(vo);
        return ResponseEntity.ok(response);
    }

    // 회원 ID 중복 체크 API
    @GetMapping("/check-id")
    public ResponseEntity<String> checkClientId(@RequestParam String clientId) {
        boolean exists = clientRepository.findByClientId(clientId).isPresent();
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

    // 회원 정보 수정 API
    @PatchMapping("/update")
    public ResponseEntity<UpdateProfileResponseVO> updateProfile(@RequestBody UpdateProfileRequestVO vo) {
        UpdateProfileResponseVO response = clientService.updateProfile(vo);
        return ResponseEntity.ok(response);
    }

    // 비밀번호 변경 API
    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResetResponseVO> resetPassword(@RequestBody PasswordResetRequestVO vo) {
        PasswordResetResponseVO response = clientService.resetPassword(vo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find-ID")
    public ResponseEntity<FindIDResponseVO> findID(@RequestBody FindIDRequestVO vo) {
        FindIDResponseVO response = clientService.findID(vo);
        return ResponseEntity.ok(response);
    }
}
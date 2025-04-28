package com.DDIS.email;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String email) {
        // 1. 인증번호 생성
        String code = generateRandomCode();

        // 2. Redis에 email -> code 저장 (TTL: 3분)
        redisTemplate.opsForValue().set(email, code, 3, TimeUnit.MINUTES);

        // 3. 메일 발송
        sendEmail(email, code);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        String savedCode = redisTemplate.opsForValue().get(email);

        // Redis에 저장된 인증번호가 존재하고, 입력값과 같으면 true
        return savedCode != null && savedCode.equals(code);
    }

    private String generateRandomCode() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000; // 6자리 랜덤 숫자
        return String.valueOf(number);
    }

    private void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[DDIS] 이메일 인증번호입니다.");
        message.setText("당신의 인증번호는 " + code + " 입니다.\n3분 안에 입력해주세요!");
        mailSender.send(message);
    }
}

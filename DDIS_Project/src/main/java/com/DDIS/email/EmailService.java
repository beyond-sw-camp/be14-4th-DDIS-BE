package com.DDIS.email;

public interface EmailService {
        void sendVerificationCode(String email);  // 이메일로 인증번호 보내기
        boolean verifyCode(String email, String code);  // 인증번호 검증
}

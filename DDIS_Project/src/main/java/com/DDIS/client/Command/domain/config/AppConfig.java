package com.DDIS.client.Command.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {      // 비밀번호 암호화를 위한 인터페이스
        return new BCryptPasswordEncoder();         // 보안성이 높은 해시 알고리즘 제공
    }
}

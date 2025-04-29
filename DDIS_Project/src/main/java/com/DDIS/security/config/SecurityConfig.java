package com.DDIS.security.config;

import com.DDIS.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// JWT 필터 등록 및 설정
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (REST API용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/clients/signup", "/clients/login","/**").permitAll() // 인증 없이 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 등록
                .formLogin(login -> login.disable()); // 기본 로그인 화면 비활성화

        return http.build();
    }
}

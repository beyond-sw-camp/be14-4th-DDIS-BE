package com.DDIS.security.config;

import com.DDIS.security.JwtAccessDeniedHandler;
import com.DDIS.security.JwtAuthenticationEntryPoint;
import com.DDIS.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 끄기 (REST API라서)

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)      // 인증 실패 처리
                        .accessDeniedHandler(jwtAccessDeniedHandler))               // 권한 실패 처리

                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))    // 세션 사용 안 함

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/clients/signup", "/clients/login").permitAll()
                                                                                    // 회원가입, 로그인만 인증 없이 허용
                        .anyRequest().authenticated())                              // 나머지는 인증 필요

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 등록

                .formLogin(formLogin -> formLogin.disable()); // 기본 로그인 폼 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


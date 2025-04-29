package com.DDIS.security.filter;

import com.DDIS.security.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;


// 토큰 인증 필터, 요청마다 JWT 인증 처리
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 요청 헤더에서 Authorization 값 추출
        String token = httpRequest.getHeader("Authorization");

        // Bearer {token} 형식 확인
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 제거

            // 토큰 유효한 경우
            if (jwtUtil.validateToken(token)) {
                String clientId = jwtUtil.getClientId(token);

                // Spring Security에 인증 객체 등록
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(clientId, null, null);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

                // SecurityContext에 인증 정보 저장
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 필터 체인 계속 실행
        chain.doFilter(request, response);
    }
}

//package com.DDIS.security.filter;
//
//import com.DDIS.security.util.JwtUtil;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilter {
//
//    private final JwtUtil jwtUtil;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        // Authorization 헤더에서 JWT 추출
//        String token = httpRequest.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // "Bearer " 제거
//
//            if (jwtUtil.validateToken(token)) {
//                String clientId = jwtUtil.getClientId(token);
//
//                // 사용자 인증 객체 생성
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(clientId, null, null);
//
//                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//
//                // SecurityContext에 인증 정보 저장
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//
//        // 필터 체인 계속 실행
//        chain.doFilter(request, response);
//    }
//}

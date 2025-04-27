//package com.DDIS.client.Command.application.service;
//
//import com.DDIS.client.Command.domain.aggregate.UserEntity;
//import com.DDIS.client.Command.domain.repository.ClientRepository;
//import com.DDIS.client.Command.domain.vo.LoginRequestVO;
//import com.DDIS.client.Command.domain.vo.LoginResponseVO;
//import com.DDIS.client.Command.domain.vo.SignupRequestVO;
//import com.DDIS.client.Command.domain.vo.SignupResponseVO;
//import com.DDIS.security.util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ClientServiceImpl implements ClientService {
//
//    private final ClientRepository clientRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    @Override
//    public SignupResponseVO signup(SignupRequestVO vo) {
//        if (clientRepository.findByClientId(vo.getClientId()).isPresent()) {
//            return new SignupResponseVO("이미 존재하는 아이디입니다.");
//        }
//
//        UserEntity user = UserEntity.builder()
//                .clientName(vo.getClientName())
//                .clientId(vo.getClientId())
//                .clientPwd(passwordEncoder.encode(vo.getClientPwd()))
//                .clientEmail(vo.getClientEmail())
//                .clientBirth(vo.getClientBirth())
//                .clientNickname(vo.getClientNickname())
//                .clientPhotoUrl(vo.getClientPhotoUrl())
//                .clientType(vo.getClientType())
//                .clientAccountStatus("ACTIVE")
//                .clientColorRgb("rgba (80, 212, 198, 100)")
//                .build();
//
//        clientRepository.save(user);
//
//        return new SignupResponseVO("회원가입이 성공적으로 완료되었습니다.");
//    }
//
//    @Override
//    public LoginResponseVO login(LoginRequestVO vo) {
//        // 사용자 아이디로 조회
//        Optional<UserEntity> optionalUser = clientRepository.findByClientId(vo.getClientId());
//
//        if (optionalUser.isEmpty()) {
//            return new LoginResponseVO(null, "존재하지 않는 사용자입니다.");
//        }
//
//        UserEntity user = optionalUser.get();
//
//        // 비밀번호 검증
//        if (!passwordEncoder.matches(vo.getClientPwd(), user.getClientPwd())) {
//            return new LoginResponseVO(null, "비밀번호가 일치하지 않습니다.");
//        }
//
//        // 로그인 성공 → JWT 발급
//        String token = jwtUtil.generateToken(user.getClientId(), user.getClientType());
//
//        return new LoginResponseVO(token, "로그인 성공");
//    }
//}
//

package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.ClientRoleEntity;
import com.DDIS.client.Command.domain.aggregate.ClientRoleId;
import com.DDIS.client.Command.domain.aggregate.RoleEntity;
import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.client.Command.domain.repository.ClientRoleRepository;
import com.DDIS.client.Command.domain.repository.RoleRepository;
import com.DDIS.client.Command.domain.vo.*;
import com.DDIS.security.config.TokenResponseVO;
import com.DDIS.security.util.JwtUtil;
import com.DDIS.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final ClientRoleRepository clientRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    // 회원 가입 메서드
    @Override
    public SignupResponseVO signup(SignupRequestVO vo) {
        // 이메일 인증 여부 체크
        String verified = redisTemplate.opsForValue().get("verified:" + vo.getClientEmail());
        if (!"true".equals(verified)) {
            return new SignupResponseVO("이메일 인증이 완료되지 않았습니다.");
        }

        // 아이디 중복 체크
        if (clientRepository.findByClientId(vo.getClientId()).isPresent()) {
            return new SignupResponseVO("이미 존재하는 아이디입니다.");
        }

        // 비밀번호 유효성 검사
        if (!isValidPassword(vo.getClientPwd())) {
            return new SignupResponseVO("비밀번호는 대소문자와 숫자를 포함해 8자리 이상이어야 합니다.");
        }

        // 회원 저장 로직
        UserEntity user = UserEntity.builder()
                .clientName(vo.getClientName())
                .clientId(vo.getClientId())
                .clientPwd(passwordEncoder.encode(vo.getClientPwd()))
                .clientEmail(vo.getClientEmail())
                .clientBirth(vo.getClientBirth())
                .clientNickname(vo.getClientNickname())
                .clientPhotoUrl(vo.getClientPhotoUrl())
                .clientType(vo.getClientType())
                .clientAccountStatus("ACTIVE")
                .clientColorRgb("rgba (80, 212, 198, 100)")
                .build();

        UserEntity savedUser = clientRepository.save(user);

        int roleNum = "ADMIN".equalsIgnoreCase(vo.getClientType()) ? 2 : 1;
        RoleEntity role = roleRepository.findById(roleNum)
                .orElseThrow(() -> new RuntimeException("권한이 존재하지 않습니다."));

        ClientRoleId id = new ClientRoleId();
        id.setClientNum(savedUser.getClientNum());
        id.setRoleNum(role.getRoleNum());

        ClientRoleEntity clientRole = new ClientRoleEntity();
        clientRole.setId(id);
        clientRole.setUser(savedUser);
        clientRole.setRole(role);

        clientRoleRepository.save(clientRole);

        return new SignupResponseVO("회원가입이 성공적으로 완료되었습니다.");
    }

    // 로그인 메서드
    @Override
    public LoginResponseVO login(LoginRequestVO vo) {
        Optional<UserEntity> optionalUser = clientRepository.findByClientId(vo.getClientId());

        if (optionalUser.isEmpty()) {
            return new LoginResponseVO(null, null, "존재하지 않는 사용자입니다.");
        }

        UserEntity user = optionalUser.get();

        if (!passwordEncoder.matches(vo.getClientPwd(), user.getClientPwd())) {
            return new LoginResponseVO(null, null, "비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.generateToken(
                user.getClientId(),
                user.getClientType(),
                user.getClientNum()
        );

        String refreshToken = jwtUtil.generateRefreshToken(user.getClientId());

        // Redis에 저장 (key: refresh:clientId)
        redisTemplate.opsForValue().set(
                "refresh:" + user.getClientId(),
                refreshToken,
                7, java.util.concurrent.TimeUnit.DAYS // TTL 7일
        );

        return new LoginResponseVO(accessToken, refreshToken, "로그인 성공");
    }

    // 비밀번호 유효성 검사 메서드
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(regex);
    }

    // 회원 정보 수정 메서드
    @Override
    public UpdateProfileResponseVO updateProfile(UpdateProfileRequestVO vo) {
        Long clientNum = SecurityUtil.getCurrentClientNum();  // clientNum을 SecurityUtil에서 추출

        Optional<UserEntity> optionalUser = clientRepository.findByClientNum(clientNum);

        if (optionalUser.isEmpty()) {
            return new UpdateProfileResponseVO("해당 사용자를 찾을 수 없습니다.");
        }

        UserEntity user = optionalUser.get();

        // 닉네임 수정
        if (vo.getNewNickname() != null && !vo.getNewNickname().isBlank()) {
            user.updateNickname(vo.getNewNickname());
        }

        // 이메일 수정
        if (vo.getNewEmail() != null && !vo.getNewEmail().isBlank()) {
            user.updateEmail(vo.getNewEmail());
        }

        clientRepository.save(user);

        return new UpdateProfileResponseVO("회원 정보가 성공적으로 수정되었습니다.");
    }

    // 아이디 찾기 메서드
    @Override
    public FindIDResponseVO findID(FindIDRequestVO vo) {
        String verified = redisTemplate.opsForValue().get("verified:" + vo.getClientEmail());

        if (!"true".equals(verified)) {
            return new FindIDResponseVO(null, "이메일 인증이 완료되지 않았습니다.");
        }

        Optional<UserEntity> optionalUser = clientRepository
                .findByClientNameAndClientEmail(vo.getClientName(), vo.getClientEmail());

        if (optionalUser.isEmpty()) {
            return new FindIDResponseVO(null, "해당 정보와 일치하는 사용자가 없습니다.");
        }

        UserEntity user = optionalUser.get();

        return new FindIDResponseVO(user.getClientId(), "입력하신 정보로 찾은 아이디입니다.");
    }

    // mypage 조회 메서드
    @Override
    public MypageResponseVO getMyPage(Long clientNum) {
        UserEntity user = clientRepository.findByClientNum(clientNum)
                .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        return new MypageResponseVO(
                user.getClientId(),
                user.getClientEmail(),
                user.getClientBirth(),
                user.getClientNickname(),
                user.getClientColorRgb()
        );
    }

    // 비밀번호 재설정 메서드
    @Override
    public PasswordResetResponseVO resetPassword(PasswordResetRequestVO vo) {
        String verified = redisTemplate.opsForValue().get("verified:" + vo.getClientEmail());

        if (!"true".equals(verified)) {
            return new PasswordResetResponseVO("이메일 인증이 완료되지 않았습니다.");
        }

        if (!isValidPassword(vo.getNewPassword())) {
            return new PasswordResetResponseVO("비밀번호는 대소문자와 숫자를 포함하여 8자 이상이어야 합니다.");
        }

        Optional<UserEntity> optionalUser = clientRepository.findByClientEmail(vo.getClientEmail());

        if (optionalUser.isEmpty()) {
            return new PasswordResetResponseVO("해당 이메일로 등록된 사용자가 없습니다.");
        }

        UserEntity user = optionalUser.get();
        user.changePassword(passwordEncoder.encode(vo.getNewPassword()));
        clientRepository.save(user);

        return new PasswordResetResponseVO("비밀번호가 성공적으로 변경되었습니다.");
    }

    // 토큰 재발급 메서드
    @Override
    public TokenResponseVO refreshAccessToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 Refresh Token입니다.");
        }

        String clientId = jwtUtil.getClientId(refreshToken);

        String storedRefreshToken = redisTemplate.opsForValue().get("refresh:" + clientId);

        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new RuntimeException("Refresh Token이 일치하지 않습니다.");
        }

        Long clientNum = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."))
                .getClientNum();

        String newAccessToken = jwtUtil.generateToken(clientId, "ROLE_USER", clientNum);

        return new TokenResponseVO(newAccessToken, refreshToken, "Access Token 재발급 완료");
    }

    // 로그아웃 메서드
    @Override
    public void logout(Long clientNum) {
        redisTemplate.delete("refresh:" + clientNum);  // clientNum을 사용하여 리프레시 토큰 삭제
    }
}
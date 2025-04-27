package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.ClientRoleEntity;
import com.DDIS.client.Command.domain.aggregate.ClientRoleId;
import com.DDIS.client.Command.domain.aggregate.RoleEntity;
import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.client.Command.domain.repository.ClientRoleRepository;
import com.DDIS.client.Command.domain.repository.RoleRepository;
import com.DDIS.client.Command.domain.vo.LoginRequestVO;
import com.DDIS.client.Command.domain.vo.LoginResponseVO;
import com.DDIS.client.Command.domain.vo.SignupRequestVO;
import com.DDIS.client.Command.domain.vo.SignupResponseVO;
import com.DDIS.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final ClientRoleRepository clientRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public SignupResponseVO signup(SignupRequestVO vo) {
        if (clientRepository.findByClientId(vo.getClientId()).isPresent()) {
            return new SignupResponseVO("이미 존재하는 아이디입니다.");
        }

        if (!isValidPassword(vo.getClientPwd())) {
            return new SignupResponseVO("비밀번호는 대소문자와 숫자를 포함해 8자리 이상이어야 합니다.");
        }

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

    @Override
    public LoginResponseVO login(LoginRequestVO vo) {
        Optional<UserEntity> optionalUser = clientRepository.findByClientId(vo.getClientId());

        if (optionalUser.isEmpty()) {
            return new LoginResponseVO(null, "존재하지 않는 사용자입니다.");
        }

        UserEntity user = optionalUser.get();

        if (!passwordEncoder.matches(vo.getClientPwd(), user.getClientPwd())) {
            return new LoginResponseVO(null, "비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.generateToken(user.getClientId(), user.getClientType());

        return new LoginResponseVO(token, "로그인 성공");
    }

    // 비밀번호 유효성 검사 메서드
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(regex);
    }
}


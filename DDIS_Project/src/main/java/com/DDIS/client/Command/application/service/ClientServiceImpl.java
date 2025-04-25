package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.client.Command.domain.vo.SignupRequestVO;
import com.DDIS.client.Command.domain.vo.SignupResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupResponseVO signup(SignupRequestVO vo) {
        if (clientRepository.findByClientId(vo.getClientId()).isPresent()) {
            return new SignupResponseVO("이미 존재하는 아이디입니다.");
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

        clientRepository.save(user);

        return new SignupResponseVO("회원가입이 성공적으로 완료되었습니다.");
    }
}


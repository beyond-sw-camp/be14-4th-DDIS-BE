package com.DDIS.security.userdetails;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String clientId) throws UsernameNotFoundException {
        UserEntity user = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new CustomUserDetails(user);
    }
}

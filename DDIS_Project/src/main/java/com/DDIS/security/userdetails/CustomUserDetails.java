package com.DDIS.security.userdetails;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long clientNum;
    private final String clientId;
    private final String password;
    private final String role;
    private final Collection<? extends GrantedAuthority> authorities;

    // 🔸 토큰 기반 인증용 생성자
    public CustomUserDetails(Long clientNum, String clientId, String role) {
        this.clientNum = clientNum;
        this.clientId = clientId;
        this.password = null; // 비밀번호 없음 (토큰 기반)
        this.role = role;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    // 🔸 DB 기반 인증용 생성자
    public CustomUserDetails(UserEntity user) {
        this.clientNum = user.getClientNum();
        this.clientId = user.getClientId();
        this.password = user.getClientPwd();
        this.role = user.getClientType(); // 예: "USER", "ADMIN"
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getClientType()));
    }

    public Long getClientNum() {
        return clientNum;
    }

    @Override
    public String getUsername() {
        return clientId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

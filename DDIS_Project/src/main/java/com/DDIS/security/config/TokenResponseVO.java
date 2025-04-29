package com.DDIS.security.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseVO {
    private String accessToken;
    private String refreshToken;
    private String message;
}

package com.DDIS.client.Command.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseVO {
    private String accessToken;
    private String refreshToken;
    private String message;
}

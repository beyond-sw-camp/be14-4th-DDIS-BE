package com.DDIS.client.Command.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseVO {
    private final String token;
    private final String message;
}

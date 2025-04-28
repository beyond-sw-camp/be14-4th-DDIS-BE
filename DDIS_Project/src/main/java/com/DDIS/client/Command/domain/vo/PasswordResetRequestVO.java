package com.DDIS.client.Command.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordResetRequestVO {
    private String email;
    private String newPassword;
}

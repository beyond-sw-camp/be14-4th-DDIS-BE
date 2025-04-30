package com.DDIS.client.Command.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordResetRequestVO {
    private String clientEmail;
    private String clientName;
    private String newPassword;
}

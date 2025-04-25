package com.DDIS.client.Command.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestVO {
    private String clientId;
    private String clientPwd;
}

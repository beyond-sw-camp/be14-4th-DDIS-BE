package com.DDIS.client.Command.domain.vo;

import lombok.Data;

@Data
public class SignupRequestVO {
    private String clientName;
    private String clientId;
    private String clientPwd;
    private String clientEmail;
    private String clientBirth;
    private String clientNickname;
    private String clientPhotoUrl;
    private String clientType; // ex: USER or ADMIN
}


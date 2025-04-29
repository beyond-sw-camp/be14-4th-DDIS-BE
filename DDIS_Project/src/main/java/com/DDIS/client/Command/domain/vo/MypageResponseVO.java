package com.DDIS.client.Command.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageResponseVO {
    private String clientId;
    private String clientEmail;
    private String clientBirth;
    private String clientNickname;
    private String clientColorRgb;
}

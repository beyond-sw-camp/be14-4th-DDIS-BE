package com.DDIS.client.Command.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProfileRequestVO {
    private String clientId; // 수정 대상 사용자 ID
    private String newNickname;
    private String newEmail;
}

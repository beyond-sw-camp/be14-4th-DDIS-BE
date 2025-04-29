package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.vo.*;

public interface ClientService {
    SignupResponseVO signup(SignupRequestVO vo);

    LoginResponseVO login(LoginRequestVO requestVO);

    UpdateProfileResponseVO updateProfile(UpdateProfileRequestVO vo);

    PasswordResetResponseVO resetPassword(PasswordResetRequestVO vo);

    MypageResponseVO getMyPage(String clientId);
}

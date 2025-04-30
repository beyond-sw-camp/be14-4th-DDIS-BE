package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.vo.*;
import com.DDIS.security.config.TokenResponseVO;

public interface ClientService {
    SignupResponseVO signup(SignupRequestVO vo);

    LoginResponseVO login(LoginRequestVO requestVO);

    UpdateProfileResponseVO updateProfile(UpdateProfileRequestVO vo);

    PasswordResetResponseVO resetPassword(PasswordResetRequestVO vo);

    FindIDResponseVO findID(FindIDRequestVO vo);

    MypageResponseVO getMyPage(String clientId);

    TokenResponseVO refreshAccessToken(String refreshToken);

    void logout(String clientId);
}

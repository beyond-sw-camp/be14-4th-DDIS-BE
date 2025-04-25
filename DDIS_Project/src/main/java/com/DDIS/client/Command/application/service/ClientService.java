package com.DDIS.client.Command.application.service;

import com.DDIS.client.Command.domain.vo.SignupRequestVO;
import com.DDIS.client.Command.domain.vo.SignupResponseVO;

public interface ClientService {
    SignupResponseVO signup(SignupRequestVO vo);
}

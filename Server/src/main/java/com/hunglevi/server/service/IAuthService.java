package com.hunglevi.server.service;

import com.hunglevi.server.dto.AuthReq;
import com.hunglevi.server.dto.MessengerRes;
import com.hunglevi.server.dto.RegisterReq;

public interface IAuthService {

    MessengerRes register(RegisterReq registrationRequest);

    MessengerRes login(AuthReq loginRequest);

    MessengerRes refreshToken(String refreshTokenReqiest);
    MessengerRes getMyInfo(String username);


    MessengerRes logout(AuthReq logoutRequest);
}
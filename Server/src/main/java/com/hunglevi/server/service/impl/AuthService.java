package com.hunglevi.server.service.impl;

import com.hunglevi.server.config.JwtUtils;
import com.hunglevi.server.dto.AuthReq;
import com.hunglevi.server.dto.MessengerRes;
import com.hunglevi.server.dto.RegisterReq;
import com.hunglevi.server.repository.RolesRepository;
import com.hunglevi.server.repository.UserRepository;
import com.hunglevi.server.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public MessengerRes register(RegisterReq registrationRequest) {
        return null;
    }

    @Override
    public MessengerRes login(AuthReq loginRequest) {
        return null;
    }

    @Override
    public MessengerRes refreshToken(AuthReq refreshTokenReqiest) {
        return null;
    }

    @Override
    public MessengerRes logout(AuthReq logoutRequest) {
        return null;
    }
}
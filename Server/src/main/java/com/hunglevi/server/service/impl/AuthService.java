package com.hunglevi.server.service.impl;

import com.hunglevi.server.config.JwtUtils;
import com.hunglevi.server.dto.AuthReq;
import com.hunglevi.server.dto.MessengerRes;
import com.hunglevi.server.dto.RegisterReq;
import com.hunglevi.server.entities.Roles;
import com.hunglevi.server.entities.Users;
import com.hunglevi.server.repository.RolesRepository;
import com.hunglevi.server.repository.UserRepository;
import com.hunglevi.server.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

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
        MessengerRes response = new MessengerRes();

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            var user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
            String token = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            Roles role = roleRepository.findById(user.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            response.setStatusCode(200);
            response.setToken(token);
            response.setRolesName(role.getName());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("30m");
            response.setMessage("Successfully Logged In");

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;    }

    @Override
    public MessengerRes refreshToken(String refreshTokenReqiest) {
        MessengerRes response = new MessengerRes();
        try {
            String userEmail = jwtUtils.extractUsername(refreshTokenReqiest);
            Users user = userRepository.findByUsername(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
            Roles role = roleRepository.findById(user.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            if (user != null) {
                if (jwtUtils.isTokenValid(refreshTokenReqiest, user)) {
                    String accessToken = jwtUtils.generateToken(user);
                    response.setStatusCode(200);
                    response.setToken(accessToken);
                    response.setRefreshToken(refreshTokenReqiest);
                    response.setRoles(role);
                    response.setExpirationTime("30M");
                    response.setMessage("Successfully Refreshed Token");
                }
            } else {
                throw new RuntimeException("Invalid User");
            }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public MessengerRes getMyInfo(String username) {
        MessengerRes reqRes = new MessengerRes();
        try {
            Optional<Users> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                reqRes.setUser(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }

    @Override
    public MessengerRes logout(AuthReq logoutRequest) {
        return null;
    }
}
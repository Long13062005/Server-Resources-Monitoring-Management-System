package com.hunglevi.server.controller;


import com.hunglevi.server.dto.AuthReq;
import com.hunglevi.server.dto.MessengerRes;
import com.hunglevi.server.entities.Alerts;
import com.hunglevi.server.entities.Roles;
import com.hunglevi.server.entities.Users;
import com.hunglevi.server.repository.RolesRepository;
import com.hunglevi.server.service.impl.AuthService;
import com.hunglevi.server.service.impl.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> getRoles() {
        List<Roles> roles = rolesRepository.findAll(); // Ensure this returns a list
        return ResponseEntity.ok(roles);
    }

//    @PostMapping("/auth/register")
//    public ResponseEntity<MessengerRes> register(@RequestBody MessengerRes registerRequest) {
//        return ResponseEntity.ok(authService.register(registerRequest));
//    }

    @PostMapping("/auth/login")
    public ResponseEntity<MessengerRes> login(@RequestBody AuthReq loginRequest, HttpServletResponse response) {
        MessengerRes result = authService.login(loginRequest);
        System.out.println("refreshToken: " + result.getRefreshToken());
        Cookie refreshTokenCookie = new Cookie("refreshToken", result.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true); // Ensure cookie is HTTP-only
        refreshTokenCookie.setPath("/");

        refreshTokenCookie.setMaxAge(30 * 60); // Set an appropriate max age (30M in this example)
        response.addCookie(refreshTokenCookie);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/auth/refresh")
    public ResponseEntity<MessengerRes> refreshToken(@CookieValue("refreshToken") String refreshToken) {
        System.out.println("refreshToken endpoint was called ");
        System.out.println("refreshToken: " + refreshToken);
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<MessengerRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        MessengerRes response = authService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
package com.hunglevi.server.controller;


import com.hunglevi.server.dto.MessengerRes;
import com.hunglevi.server.service.impl.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<MessengerRes> register(@RequestBody MessengerRes registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<MessengerRes> login(@RequestBody MessengerRes loginRequest, HttpServletResponse response) {
        MessengerRes result = authService.login(loginRequest);
        System.out.println("refreshToken: "+ result.getRefreshToken());
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
        System.out.println("refreshToken: "+ refreshToken);
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
    }


    @GetMapping("/admin/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessengerRes> getAllUsers(){
        System.out.println("the controller is called");
        return ResponseEntity.ok(authService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessengerRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(authService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessengerRes> updateUser(@PathVariable Integer userId, @RequestBody Users reqres){
        return ResponseEntity.ok(authService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<MessengerRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        MessengerRes response = authService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessengerRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(authService.deleteUser(userId));
    }

}
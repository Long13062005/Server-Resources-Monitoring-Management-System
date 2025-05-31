package com.hunglevi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String username;
    private String password;
    private String role;
    private String email;

    private boolean deleted = false;
}
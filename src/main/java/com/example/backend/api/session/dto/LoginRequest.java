package com.example.backend.api.session.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}

package com.example.backend.api.member.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Provider provider;
}

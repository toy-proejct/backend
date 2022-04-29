package com.example.backend.fixtures;

import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.LoginRequest;

public class AuthFixture {

    public static LoginRequest.LoginRequestBuilder loginRequest() {
        return LoginRequest.builder()
                .email("test@test.com")
                .password("1q2w3e4r")
                .providerRequest(LoginRequest.ProviderRequest.builder()
                        .providerType(ProviderType.DEFAULT)
                        .token("")
                        .build()
                );

    }
}

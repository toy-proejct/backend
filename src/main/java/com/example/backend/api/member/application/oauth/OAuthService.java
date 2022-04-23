package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.ProviderType;

public interface OAuthService {

    String login(LoginRequest loginRequest);

    ProviderType getProviderType();
}

package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.domain.ProviderType;

public interface OAuthService {

    Member login(LoginRequest loginRequest);

    ProviderType getProviderType();
}

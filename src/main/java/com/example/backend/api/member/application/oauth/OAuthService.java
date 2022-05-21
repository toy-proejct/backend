package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.RegisterMemberRequest;

public interface OAuthService {

    Member login(LoginRequest loginRequest);

    void register(RegisterMemberRequest registerMemberRequest);

    ProviderType getProviderType();
}

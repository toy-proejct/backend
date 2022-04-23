package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.ProviderType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NaverOAuthService implements OAuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public NaverOAuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        // TODO : 네이버 로그인 구현
        return "";
    }

    @Override
    public ProviderType getProviderType() {
        return ProviderType.NAVER;
    }
}
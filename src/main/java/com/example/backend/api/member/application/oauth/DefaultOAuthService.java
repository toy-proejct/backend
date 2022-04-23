package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.ProviderType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultOAuthService implements OAuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultOAuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        member.checkPassword(loginRequest.getPassword(), passwordEncoder);

        return member.getEmail();
    }

    @Override
    public ProviderType getProviderType() {
        return ProviderType.NORMAL;
    }
}

package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultOAuthService implements OAuthService {
    private static final ProviderType PROVIDER_TYPE = ProviderType.DEFAULT;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final OAuthValidator oAuthValidator;

    public DefaultOAuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, OAuthValidator oAuthValidator) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.oAuthValidator = oAuthValidator;
    }

    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        oAuthValidator.validate(member, PROVIDER_TYPE);
        member.checkPassword(loginRequest.getPassword(), passwordEncoder);

        return member;
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

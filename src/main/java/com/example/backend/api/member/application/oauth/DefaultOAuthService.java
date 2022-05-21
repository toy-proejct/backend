package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.*;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultOAuthService implements OAuthService {
    private static final ProviderType PROVIDER_TYPE = ProviderType.DEFAULT;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final OauthRepository oauthRepository;
    private final OAuthValidator oAuthValidator;

    public DefaultOAuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, OauthRepository oauthRepository, OAuthValidator oAuthValidator) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.oauthRepository = oauthRepository;
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
    public void register(RegisterMemberRequest registerMemberRequest) {
        Member member = registerMemberRequest.toMemberEntity(passwordEncoder);
        memberRepository.save(member);

        Oauth oauth = registerMemberRequest.toOauthEntity(member);
        oauthRepository.save(oauth);
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

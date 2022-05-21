package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import org.springframework.stereotype.Service;

@Service
public class NaverOAuthService implements OAuthService {
    private static final ProviderType PROVIDER_TYPE = ProviderType.NAVER;

    private final MemberRepository memberRepository;
    private final OAuthValidator oAuthValidator;

    public NaverOAuthService(MemberRepository memberRepository, OAuthValidator oAuthValidator) {
        this.memberRepository = memberRepository;
        this.oAuthValidator = oAuthValidator;
    }

    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        oAuthValidator.validate(member, PROVIDER_TYPE);

        // TODO : 네이버 로그인 구현 해야함

        return member;
    }

    @Override
    public void register(RegisterMemberRequest registerMemberRequest) {
        // TODO : 네이버 회원가입 구현 해야함
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

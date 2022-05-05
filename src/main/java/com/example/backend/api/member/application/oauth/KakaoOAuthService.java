package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.*;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.kakao.KakaoMemberInfo;
import com.example.backend.api.member.infrastructure.KakaoClient;
import com.example.backend.common.security.BearerHeader;
import org.springframework.stereotype.Service;

@Service
public class KakaoOAuthService implements OAuthService {
    private static final ProviderType PROVIDER_TYPE = ProviderType.KAKAO;

    private final MemberRepository memberRepository;
    private final KakaoClient kakaoClient;
    private final OAuthValidator oAuthValidator;

    public KakaoOAuthService(MemberRepository memberRepository, KakaoClient kakaoClient, OAuthValidator oAuthValidator) {
        this.memberRepository = memberRepository;
        this.kakaoClient = kakaoClient;
        this.oAuthValidator = oAuthValidator;
    }

    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        oAuthValidator.validate(member, PROVIDER_TYPE);

        KakaoMemberInfo kakaoClientUserInfo = kakaoClient.getUserInfo(BearerHeader.of(loginRequest.getProviderRequest().getToken()));
        member.checkEmail(kakaoClientUserInfo.getKakao_account());

        return member;
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

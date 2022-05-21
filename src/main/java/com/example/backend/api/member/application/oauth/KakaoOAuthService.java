package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.*;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.kakao.KakaoMemberInfo;
import com.example.backend.api.member.infrastructure.KakaoClient;
import com.example.backend.common.security.BearerHeader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KakaoOAuthService implements OAuthService {
    private static final ProviderType PROVIDER_TYPE = ProviderType.KAKAO;

    private final MemberRepository memberRepository;
    private final OauthRepository oauthRepository;
    private final KakaoClient kakaoClient;
    private final PasswordEncoder passwordEncoder;
    private final OAuthValidator oAuthValidator;

    public KakaoOAuthService(MemberRepository memberRepository, OauthRepository oauthRepository, KakaoClient kakaoClient, PasswordEncoder passwordEncoder, OAuthValidator oAuthValidator) {
        this.memberRepository = memberRepository;
        this.oauthRepository = oauthRepository;
        this.kakaoClient = kakaoClient;
        this.passwordEncoder = passwordEncoder;
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
    public void register(RegisterMemberRequest registerMemberRequest) {
        KakaoMemberInfo kakaoClientUserInfo = kakaoClient.getUserInfo(BearerHeader.of(registerMemberRequest.getOauthRequest().getAccessToken()));
        String email = kakaoClientUserInfo.getKakao_account()
                .getEmail();

        Member member = registerMemberRequest.toMemberEntity(email, passwordEncoder);
        memberRepository.save(member);

        Oauth oauth = registerMemberRequest.toOauthEntity(member);
        oauthRepository.save(oauth);
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

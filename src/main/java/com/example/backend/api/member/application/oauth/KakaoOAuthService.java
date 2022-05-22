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
        String email = getEmail(loginRequest.getProviderRequest().getToken());

        Member member = memberRepository.getByEmailWithCheck(email);
        oAuthValidator.validate(member, PROVIDER_TYPE);

        return member;
    }

    @Override
    public void register(RegisterMemberRequest registerMemberRequest) {
        String email = getEmail(registerMemberRequest.getOauthRequest().getAccessToken());

        Member member = registerMemberRequest.toMemberEntity(email, passwordEncoder);
        memberRepository.save(member);

        Oauth oauth = registerMemberRequest.toOauthEntity(member);
        oauthRepository.save(oauth);
    }

    private String getEmail(String loginRequest) {
        KakaoMemberInfo kakaoClientUserInfo = kakaoClient.getUserInfo(BearerHeader.of(loginRequest));
        return kakaoClientUserInfo.getKakao_account()
                .getEmail();
    }

    @Override
    public ProviderType getProviderType() {
        return PROVIDER_TYPE;
    }
}

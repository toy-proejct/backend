package com.example.backend.api.member.application;

import com.example.backend.api.member.application.oauth.OAuthService;
import com.example.backend.api.member.application.oauth.SocialOAuthFactory;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.TokenResponse;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import com.example.backend.common.exception.LoginFailedException;
import com.example.backend.common.security.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final SocialOAuthFactory socialOAuthFactory;
    private final JwtService jwtService;

    public MemberService(SocialOAuthFactory socialOAuthFactory, JwtService jwtService) {
        this.socialOAuthFactory = socialOAuthFactory;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest loginRequest) {
        OAuthService oAuthService = socialOAuthFactory.getSocialService(loginRequest.getProviderType());

        Member member = oAuthService.login(loginRequest);

        if (member.isBanned()) {
            throw new LoginFailedException("정지된 사용자입니다");
        }

        return new TokenResponse(jwtService.createToken(member.getEmail()));
    }

    public void registerMember(RegisterMemberRequest registerMemberRequest) {
        OAuthService oAuthService = socialOAuthFactory.getSocialService(registerMemberRequest.getOauthRequest().getProviderType());

        oAuthService.register(registerMemberRequest);
    }

    public void updateMember(Member member, UpdateMemberRequest updateMemberRequest) {
        member.update(updateMemberRequest);
    }
}

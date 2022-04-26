package com.example.backend.api.member.application;

import com.example.backend.api.member.application.oauth.OAuthService;
import com.example.backend.api.member.application.oauth.SocialOAuthFactory;
import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.domain.Oauth;
import com.example.backend.api.member.domain.OauthRepository;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.TokenResponse;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import com.example.backend.common.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final OauthRepository oauthRepository;
    private final PasswordEncoder passwordEncoder;
    private final SocialOAuthFactory socialOAuthFactory;
    private final JwtService jwtService;

    public MemberService(MemberRepository memberRepository, OauthRepository oauthRepository, PasswordEncoder passwordEncoder, SocialOAuthFactory socialOAuthFactory, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.oauthRepository = oauthRepository;
        this.passwordEncoder = passwordEncoder;
        this.socialOAuthFactory = socialOAuthFactory;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest loginRequest) {
        OAuthService OAuthService = socialOAuthFactory.getSocialService(loginRequest.getProviderType());

        String email = OAuthService.login(loginRequest);

        return new TokenResponse(jwtService.createToken(email));
    }

    public void registerMember(RegisterMemberRequest registerMemberRequest) {
        Member member = registerMemberRequest.toMemberEntity(passwordEncoder);
        memberRepository.save(member);

        Oauth oauth = registerMemberRequest.toOauthEntity(member);
        oauthRepository.save(oauth);
    }

    public void updateMember(Member member, UpdateMemberRequest updateMemberRequest) {
        member.update(updateMemberRequest);
    }
}

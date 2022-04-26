package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.*;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.common.exception.UnidentifiedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultOAuthService implements OAuthService {
    private final MemberRepository memberRepository;
    private final OauthRepository oauthRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultOAuthService(MemberRepository memberRepository, OauthRepository oauthRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.oauthRepository = oauthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());

        oauthRepository.findByMemberAndProviderType(member, ProviderType.DEFAULT)
                .orElseThrow(() -> {
                    Oauth oauth = oauthRepository.findByMember(member)
                            .stream()
                            .findFirst()
                            .orElseThrow(() -> {
                                        throw new UnidentifiedException("유효한 로그인 방식이 없습니다");
                                    }
                            );

                    throw new UnidentifiedException(oauth.getProviderType() + "방식으로 로그인 하세요");
                });

        member.checkPassword(loginRequest.getPassword(), passwordEncoder);

        return member.getEmail();
    }

    @Override
    public ProviderType getProviderType() {
        return ProviderType.DEFAULT;
    }
}

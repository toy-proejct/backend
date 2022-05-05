package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.Oauth;
import com.example.backend.api.member.domain.OauthRepository;
import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.common.exception.LoginFailedException;
import org.springframework.stereotype.Component;

@Component
public class OAuthValidator {
    private final OauthRepository oauthRepository;

    public OAuthValidator(OauthRepository oauthRepository) {
        this.oauthRepository = oauthRepository;
    }

    public void validate(Member member, ProviderType providerType) {
        oauthRepository.findByMemberAndProviderType(member, providerType)
                .orElseThrow(() -> {
                    Oauth oauth = oauthRepository.findByMember(member)
                            .stream()
                            .findFirst()
                            .orElseThrow(() -> {
                                        throw new LoginFailedException("유효한 로그인 방식이 없습니다");
                                    }
                            );

                    throw new LoginFailedException(oauth.getProviderType() + "방식으로 로그인 하세요");
                });
    }
}

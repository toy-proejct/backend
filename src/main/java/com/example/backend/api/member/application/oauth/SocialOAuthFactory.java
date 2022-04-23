package com.example.backend.api.member.application.oauth;

import com.example.backend.api.member.dto.ProviderType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SocialOAuthFactory {

    private final Map<ProviderType, OAuthService> socialServiceMap = new HashMap<>();

    public SocialOAuthFactory(List<OAuthService> oAuthServices) {
        if (oAuthServices.isEmpty()) {
            throw new RuntimeException("OAuth Bean 등록 실패");
        }

        for (OAuthService OAuthService : oAuthServices) {
            socialServiceMap.put(OAuthService.getProviderType(), OAuthService);
        }
    }

    public OAuthService getSocialService(ProviderType providerType) {
        return socialServiceMap.get(providerType);
    }
}

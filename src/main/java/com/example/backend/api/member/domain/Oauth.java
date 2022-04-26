package com.example.backend.api.member.domain;

import com.example.backend.api.infra.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth extends BaseEntity {
    @ManyToOne
    private Member member;

    private String snsId;

    private String accessToken;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    public Oauth(Member member, String snsId, String accessToken, ProviderType providerType) {
        this.member = member;
        this.snsId = snsId;
        this.accessToken = accessToken;
        this.providerType = providerType;
    }

    public Member getMember() {
        return member;
    }

    public String getSnsId() {
        return snsId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public ProviderType getProviderType() {
        return providerType;
    }
}

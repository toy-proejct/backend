package com.example.backend.api.member.dto;

import lombok.Data;

@Data
public class Provider {
    private ProviderType providerType;
    private String token;

    public Provider(ProviderType providerType, String token) {
        this.providerType = providerType;
        this.token = token;
    }
}

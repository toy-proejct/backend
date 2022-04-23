package com.example.backend.api.member.dto;

public enum ProviderType {
    NORMAL(""),
    KAKAO(""),
    NAVER("");

    private final String url;

    ProviderType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

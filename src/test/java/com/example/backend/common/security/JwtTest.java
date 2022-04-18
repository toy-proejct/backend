package com.example.backend.common.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JwtTest {
    private static final String EMAIL = "test@test.com";

    @Test
    @DisplayName("생성된 토큰의 claim값이 입력한 값과 일치한다")
    public void createToken() {
        //given
        JwtService jwtService = new JwtService();
        String token = jwtService.createToken(EMAIL);

        //when
        String claimEmail = jwtService.getClaimEmail(token);

        //then
        추출한_이메일과_입력한_이메일이_같음(claimEmail);
    }

    @Test
    @DisplayName("토큰 유효시간이 지나면 토큰 유효성 검증에 실패한다")
    public void expirationTest() {
        //given
        JwtService jwtService = new JwtService(-10L);
        String token = jwtService.createToken(EMAIL);

        //when
        boolean validToken = jwtService.isValidToken(token);

        //then
        토큰이_유효하지_않음(validToken);
    }

    private void 추출한_이메일과_입력한_이메일이_같음(String claimEmail) {
        assertThat(claimEmail).isEqualTo(EMAIL);
    }

    private void 토큰이_유효하지_않음(boolean validToken) {
        assertThat(validToken).isFalse();
    }
}

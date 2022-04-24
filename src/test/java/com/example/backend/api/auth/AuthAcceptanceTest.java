package com.example.backend.api.auth;

import com.example.backend.AcceptanceTest;
import com.example.backend.api.member.dto.MemberResponse;
import com.example.backend.api.member.dto.Provider;
import com.example.backend.api.member.dto.ProviderType;
import com.example.backend.api.member.dto.TokenResponse;
import com.example.backend.common.security.BearerHeader;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.example.backend.api.member.MemberAcceptanceTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "test@test.com";
    private static final String NICKNAME = "test";
    private static final String PHONE = "010-0000-0000";
    private static final String PASSWORD = "1q2w3e4r";
    private static final String INTRODUCE = "test introduce";
    private static final String AUTHORIZATION = "authorization";
    private static final Provider PROVIDER = new Provider(ProviderType.NORMAL, "");

    @Test
    @DisplayName("회원 정보 조회에 성공한다")
    public void getMemberInfo() {
        //given
        회원_등록되어_있음(EMAIL, NICKNAME, PHONE, PASSWORD, INTRODUCE);
        TokenResponse token = 로그인되어_있음(EMAIL, PASSWORD, PROVIDER);

        //when
        ExtractableResponse<Response> response = 회원정보를_요청(token.getToken());

        //then
        회원정보_조회됨(response, EMAIL, NICKNAME, PHONE);
    }

    @Test
    @DisplayName("토큰이 없으면 회원 정보조회에 실패한다")
    public void myInfoFailWithoutBearerToken() {
        //given
        회원_등록되어_있음(EMAIL, NICKNAME, PHONE, PASSWORD, INTRODUCE);

        //then
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/member")
                .then()
                .log().all()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("잘못된 정보를 입력하면 로그인에 실패한다")
    public void loginFail() {
        //given
        회원_등록되어_있음(EMAIL, NICKNAME, PHONE, PASSWORD, INTRODUCE);

        Map<String, Object> params = new HashMap<>();
        params.put("email", EMAIL + ".com");
        params.put("password", PASSWORD);
        params.put("provider", Map.of(
                "providerType", PROVIDER.getProviderType(),
                "token", PROVIDER.getToken()
        ));

        //then
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .post("/api/member/login")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    public void 회원_등록되어_있음(String email, String nickname, String phone, String password, String introduce) {
        회원_생성을_요청(email, nickname, phone, password, introduce);
    }

    public TokenResponse 로그인되어_있음(String email, String password, Provider provider) {
        ExtractableResponse<Response> response = 회원_로그인을_요청(email, password, provider);
        return response.as(TokenResponse.class);
    }

    public static ExtractableResponse<Response> 회원정보를_요청(String token) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION, BearerHeader.of(token))
                .when()
                .get("/api/member")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    private void 회원정보_조회됨(ExtractableResponse<Response> response, String email, String nickname, String phone) {
        MemberResponse memberResponse = response.as(MemberResponse.class);
        assertThat(memberResponse.getEmail()).isEqualTo(email);
        assertThat(memberResponse.getNickname()).isEqualTo(nickname);
        assertThat(memberResponse.getPhone()).isEqualTo(phone);
    }

}

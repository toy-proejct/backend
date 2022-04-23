package com.example.backend.api.member;

import com.example.backend.AcceptanceTest;
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

import static org.assertj.core.api.Assertions.assertThat;

public class MemberAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "test@test.com";
    private static final String NICKNAME = "test";
    private static final String PHONE = "010-0000-0000";
    private static final String PASSWORD = "1q2w3e4r";
    private static final String INTRODUCE = "test introduce";
    private static final String AUTHORIZATION = "authorization";
    private static final Provider PROVIDER = new Provider(ProviderType.NORMAL, "");

    @Test
    @DisplayName("회원 정보를 관리한다.")
    void manageMember() {
        // when
        ExtractableResponse<Response> createResponse = 회원_생성을_요청(EMAIL, NICKNAME, PHONE, PASSWORD, INTRODUCE);
        // then
        회원_생성됨(createResponse);

        //when
        ExtractableResponse<Response> loginResponse = 회원_로그인을_요청(EMAIL, PASSWORD, PROVIDER);
        //then
        로그인_성공함(loginResponse);

        //when
        ExtractableResponse<Response> updateResponse = 회원_정보수정을_요청("new nickname", "new introduce", loginResponse.as(TokenResponse.class));
        //then
        회원정보_변경_확인됨(updateResponse);

    }

    public static ExtractableResponse<Response> 회원_생성을_요청(String email, String nickname, String phone, String password, String introduce) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("nickname", nickname);
        params.put("phone", phone);
        params.put("password", password);
        params.put("introduce", introduce);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .post("/api/member/register")
                .then()
                .log().all()
                .extract();
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static ExtractableResponse<Response> 회원_로그인을_요청(String email, String password, Provider provider) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("provider", Map.of(
                "providerType", provider.getProviderType(),
                "token", provider.getToken()
        ));

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .post("/api/member/login")
                .then()
                .log().all()
                .extract();
    }

    private void 로그인_성공함(ExtractableResponse<Response> loginResponse) {
        assertThat(loginResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> 회원_정보수정을_요청(String nickname, String introduce, TokenResponse token) {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", nickname);
        params.put("introduce", introduce);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION, BearerHeader.of(token.getToken()))
                .body(params)
                .when()
                .put("/api/member")
                .then()
                .log().all()
                .extract();
    }

    private void 회원정보_변경_확인됨(ExtractableResponse<Response> updateResponse) {
        assertThat(updateResponse.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }
}

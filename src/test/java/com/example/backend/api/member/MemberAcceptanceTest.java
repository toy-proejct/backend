package com.example.backend.api.member;

import com.example.backend.AcceptanceTest;
import com.example.backend.api.fixtures.AuthFixture;
import com.example.backend.api.fixtures.MemberFixture;
import com.example.backend.api.member.domain.ProviderType;
import com.example.backend.api.member.dto.LoginRequest;
import com.example.backend.api.member.dto.RegisterMemberRequest;
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

import static com.example.backend.api.fixtures.AuthFixture.*;
import static com.example.backend.api.fixtures.MemberFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MemberAcceptanceTest extends AcceptanceTest {
    private static final String AUTHORIZATION = "authorization";

    @Test
    @DisplayName("회원 정보를 관리한다.")
    void manageMember() {
        // when
        RegisterMemberRequest registerMemberRequest = aMember().build();
        ExtractableResponse<Response> createResponse = 회원_생성을_요청(registerMemberRequest);
        // then
        회원_생성됨(createResponse);

        //when
        LoginRequest loginRequest = loginRequest().build();
        ExtractableResponse<Response> loginResponse = 회원_로그인을_요청(loginRequest);
        //then
        로그인_성공함(loginResponse);

        //when
        ExtractableResponse<Response> updateResponse = 회원_정보수정을_요청("new nickname", "new introduce", loginResponse.as(TokenResponse.class));
        //then
        회원정보_변경_확인됨(updateResponse);

    }

    public static ExtractableResponse<Response> 회원_생성을_요청(RegisterMemberRequest registerMemberRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(registerMemberRequest)
                .when()
                .post("/api/member/register")
                .then()
                .log().all()
                .extract();
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static ExtractableResponse<Response> 회원_로그인을_요청(LoginRequest loginRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginRequest)
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

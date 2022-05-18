package com.example.backend.api.craftshop;

import com.example.backend.AcceptanceTest;
import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;
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

import static com.example.backend.api.member.MemberAcceptanceTest.회원_로그인을_요청;
import static com.example.backend.api.member.MemberAcceptanceTest.회원_생성을_요청;
import static com.example.backend.fixtures.AuthFixture.loginRequest;
import static com.example.backend.fixtures.CraftShopFixture.aRegisterCraftShopRequest;
import static com.example.backend.fixtures.MemberFixture.aMember;

public class CraftShopAcceptanceTest extends AcceptanceTest {
    private static final String AUTHORIZATION = "authorization";

    @Test
    @DisplayName("공방 등록에 성공한다")
    public void registerCraftShop() {
        //given
        TokenResponse token = 회원등록_후_토큰정보를_가져옴();
        RegisterCraftShopRequest registerCraftShopRequest = aRegisterCraftShopRequest().build();

        //then
        공방등록되어_있음(token.getToken(), registerCraftShopRequest);
    }

    private TokenResponse 회원등록_후_토큰정보를_가져옴() {
        RegisterMemberRequest registerMemberRequest = aMember().build();
        회원_등록되어_있음(registerMemberRequest);
        LoginRequest loginRequest = loginRequest().build();
        return 로그인되어_있음(loginRequest);
    }

    public void 회원_등록되어_있음(RegisterMemberRequest registerMemberRequest) {
        회원_생성을_요청(registerMemberRequest);
    }

    public TokenResponse 로그인되어_있음(LoginRequest loginRequest) {
        ExtractableResponse<Response> response = 회원_로그인을_요청(loginRequest);
        return response.as(TokenResponse.class);
    }

    public ExtractableResponse<Response> 공방등록되어_있음(String token, RegisterCraftShopRequest registerCraftShopRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION, BearerHeader.of(token))
                .body(registerCraftShopRequest)
                .when()
                .post("/api/craftShop/register")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }
}

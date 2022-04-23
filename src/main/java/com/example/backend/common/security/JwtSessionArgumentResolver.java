package com.example.backend.common.security;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.common.security.annotations.MemberClaim;
import com.example.backend.common.utils.Pair;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Component
public class JwtSessionArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String BEARER = "bearer";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    public JwtSessionArgumentResolver(JwtService jwtService, MemberRepository memberRepository) {
        this.jwtService = jwtService;
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MemberClaim.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = extractBearerToken(webRequest);

        String claimEmail = jwtService.getClaimEmail(token);
        return memberRepository.findByEmail(claimEmail)
                .orElse(Member.DummyMember());
    }

    private String extractBearerToken(NativeWebRequest request) throws IllegalAccessException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);

        if (header == null) {
            return null;
        }

        Pair<String, String> pair = BearerHeader.splitToTokenFormat(header);

        if (!pair.getFirst().equals(BEARER)) {
            throw new IllegalAccessException();
        }

        return pair.getSecond();
    }
}

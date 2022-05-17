package com.example.backend.common.security;

import com.example.backend.common.exception.IllegalTokenException;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.utils.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    private static final String BEARER = "bearer";

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod) || !isAuthenticationPresent((HandlerMethod) handler)) {
            return true;
        }

        String token = extractBearerToken(request);

        if (!jwtService.isValidToken(token)) {
            throw new IllegalTokenException("토큰이 유효하지 않습니다");
        }

        return true;
    }

    private boolean isAuthenticationPresent(HandlerMethod handler) {
        return handler.hasMethodAnnotation(Authenticated.class) || handler.getBeanType().isAnnotationPresent(Authenticated.class);
    }

    private String extractBearerToken(HttpServletRequest request) throws IllegalAccessException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Pair<String, String> pair = BearerHeader.splitToTokenFormat(header);
        if (!pair.getFirst().equals(BEARER)) {
            throw new IllegalAccessException();
        }

        return pair.getSecond();
    }
}

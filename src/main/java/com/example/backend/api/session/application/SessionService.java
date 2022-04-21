package com.example.backend.api.session.application;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.session.dto.LoginRequest;
import com.example.backend.api.session.dto.TokenResponse;
import com.example.backend.common.security.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public SessionService(MemberRepository memberRepository, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        member.checkPassword(loginRequest.getPassword());

        return new TokenResponse(jwtService.createToken(member.getEmail()));
    }
}

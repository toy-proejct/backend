package com.example.backend.api.session.application;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.session.dto.LoginRequest;
import com.example.backend.common.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public SessionService(MemberRepository memberRepository, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.getByEmailWithCheck(loginRequest.getEmail());
        member.checkPassword(loginRequest.getPassword());

        return jwtService.createToken(member.getEmail());
    }
}

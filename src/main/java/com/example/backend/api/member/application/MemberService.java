package com.example.backend.api.member.application;

import com.example.backend.api.member.domain.Member;
import com.example.backend.api.member.domain.MemberRepository;
import com.example.backend.api.member.dto.RegisterMemberRequest;
import com.example.backend.api.member.dto.UpdateMemberRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerMember(RegisterMemberRequest registerMemberRequest) {
        Member member = registerMemberRequest.toEntity(passwordEncoder);
        memberRepository.save(member);
    }

    public void updateMember(Member member, UpdateMemberRequest updateMemberRequest) {
        member.update(updateMemberRequest);
    }
}

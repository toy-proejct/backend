package com.example.backend.api.member.domain;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDomain {
    private final MemberRepository memberRepository;

    public MemberDomain(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

}

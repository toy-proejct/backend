package com.example.backend.api.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    default Member getByIdWithCheck(Long id) {
        return findById(id).orElseThrow(() -> {throw new NoSuchElementException("등록된 계정이 없습니다");});
    }

    default Member getByEmailWithCheck(String email) {
        return findByEmail(email).orElseThrow(() -> {throw new NoSuchElementException("등록된 계정이 없습니다");});
    }
}

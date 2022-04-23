package com.example.backend.api.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    default Member getByIdWithCheck(Long id) {
        return findById(id).orElseThrow(NoSuchElementException::new);
    }

    default Member getByEmailWithCheck(String email) {
        return findByEmail(email).orElseThrow(NoSuchElementException::new);
    }
}

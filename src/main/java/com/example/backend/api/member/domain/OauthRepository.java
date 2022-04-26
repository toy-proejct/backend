package com.example.backend.api.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
    Optional<Oauth> findByMemberAndProviderType(Member member, ProviderType providerType);

    List<Oauth> findByMember(Member member);
}

package com.security.core.repository;

import com.security.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUsername(String username);
}

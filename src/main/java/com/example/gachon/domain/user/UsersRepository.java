package com.example.gachon.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsBySocial(String social);

    Optional<Users> findBySocial(String s);

    Optional<Users> findById(Long userIdd);

    Optional<Users> findByEmail(String key);
}

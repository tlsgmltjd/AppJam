package com.example.appjam.repository;

import com.example.appjam.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByToken(String token);

    Optional<UserEntity> findByToken(String token);
}

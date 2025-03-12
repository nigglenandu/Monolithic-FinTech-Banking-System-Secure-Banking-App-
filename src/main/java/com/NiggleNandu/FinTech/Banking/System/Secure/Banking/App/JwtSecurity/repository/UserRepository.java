package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.repository;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

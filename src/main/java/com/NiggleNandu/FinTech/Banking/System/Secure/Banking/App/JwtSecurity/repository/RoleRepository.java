package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.repository;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.Role;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(Role role);
}

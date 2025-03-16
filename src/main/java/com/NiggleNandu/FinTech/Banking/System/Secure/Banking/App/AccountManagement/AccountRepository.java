package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long AccountNumber);
}

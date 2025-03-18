package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.LoanManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}

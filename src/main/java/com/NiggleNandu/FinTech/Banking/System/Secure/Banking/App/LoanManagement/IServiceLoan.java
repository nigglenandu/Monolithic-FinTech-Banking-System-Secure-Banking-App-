package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.LoanManagement;

import java.math.BigDecimal;
import java.util.Optional;

public interface IServiceLoan {
    Loan applyForLoan(Long userId, BigDecimal amount);
    Optional<Loan> approveLoan(Long loanId);
    Optional<Loan> rejectLoan(Long loanId);
    Optional<Loan> repayLoan(Long loanId);
    Optional<Loan> getLoansByUser(Long userId);
}
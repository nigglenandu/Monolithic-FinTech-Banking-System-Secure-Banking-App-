package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.LoanManagement;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final IServiceLoan serviceLoan;
    private final HttpSecurity httpSecurity;

    public LoanController(IServiceLoan serviceLoan, HttpSecurity httpSecurity) {
        this.serviceLoan = serviceLoan;
        this.httpSecurity = httpSecurity;
    }

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@RequestParam Long userId, @RequestParam BigDecimal amount){
        return ResponseEntity.ok(serviceLoan.applyForLoan(userId, amount));
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId){
        return serviceLoan.approveLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/rejected/{loanId")
    public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId){
        return serviceLoan.rejectLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/repay/{loanId}")
    public ResponseEntity<Loan> repayLoan(@PathVariable Long loanId){
        return serviceLoan.repayLoan(loanId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoanByUser(@PathVariable Long userId){
        return ResponseEntity.ok(serviceLoan.getLoansByUser(userId));
    }

}

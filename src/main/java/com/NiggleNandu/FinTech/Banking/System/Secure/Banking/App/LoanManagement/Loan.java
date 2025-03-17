package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.LoanManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDate applicationDate;
    private LocalDate approvalDate;

    public Loan(BigDecimal amount, LocalDate applicationDate, LocalDate approvalDate, Long id, String status, Long userId) {
        this.amount = amount;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.id = id;
        this.status = status;
        this.userId = userId;
    }

    public Loan() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

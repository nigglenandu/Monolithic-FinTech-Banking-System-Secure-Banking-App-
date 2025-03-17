package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number")
    private String accountNumber;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    public Account(String accountNumber, BigDecimal balance, long id, AccountStatus status) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.id = id;
        this.status = status;
    }

    public Account() {
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

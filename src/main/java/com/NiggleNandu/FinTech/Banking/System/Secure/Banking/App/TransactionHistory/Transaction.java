package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.TransactionHistory;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.AccountManagement.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    private String type;
    private Double amount;
    private LocalDateTime timestamp;
    private String status;
    private String description;

    public Transaction(Account account, Double amount, String description, Long id, String status, LocalDateTime timestamp, String type) {
        this.account = account;
        this.amount = amount;
        this.description = description;
        this.id = id;
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Transaction() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

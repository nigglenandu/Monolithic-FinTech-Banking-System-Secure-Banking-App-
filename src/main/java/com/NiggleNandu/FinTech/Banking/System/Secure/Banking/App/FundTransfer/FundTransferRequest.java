package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer;

import java.math.BigDecimal;

public class FundTransferRequest {
    private String fromAccountNumber;
    private String toAccountId;
    private BigDecimal amount;
    private boolean isExternalTransfer;

    public FundTransferRequest(BigDecimal amount, String fromAccountNumber, boolean isExternalTransfer, String toAccountId) {
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.isExternalTransfer = isExternalTransfer;
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public boolean isExternalTransfer() {
        return isExternalTransfer;
    }

    public void setExternalTransfer(boolean externalTransfer) {
        isExternalTransfer = externalTransfer;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }
}

package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer;

import java.math.BigDecimal;

public class FundTransferRequest {
    private Long fromAccountNumber;
    private Long toAccountId;
    private BigDecimal amount;
    private boolean isExternalTransfer;

    public FundTransferRequest(BigDecimal amount, Long fromAccountNumber, boolean isExternalTransfer, Long toAccountId) {
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

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public boolean isExternalTransfer() {
        return isExternalTransfer;
    }

    public void setExternalTransfer(boolean externalTransfer) {
        isExternalTransfer = externalTransfer;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }
}

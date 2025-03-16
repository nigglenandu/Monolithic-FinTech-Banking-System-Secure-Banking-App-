package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer;

public class ExternalTransferResponse {
    private boolean success;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.payload;

import jakarta.validation.constraints.NotNull;

public class LogoutRequest {

    @NotNull(message = "Token is required")
    private String token;
}

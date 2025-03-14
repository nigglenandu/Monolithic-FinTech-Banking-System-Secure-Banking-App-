package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.payload;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String email, List<String> roles, String token, String type, Long userId, String username) {
        this.email = email;
        this.roles = roles;
        this.token = token;
        this.type = type;
        this.userId = userId;
        this.username = username;
    }

    public JwtResponse(String jwt, List<String> roles, long userId, String username, String email) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

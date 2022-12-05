package com.example.educationapp.models.token;

public class Token {

    private boolean success;
    private String token;

    public Token(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Authentication_Token{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}

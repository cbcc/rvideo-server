package cn.scau.rvideo.server.auth.controller;

public class AuthResponse {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public AuthResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}

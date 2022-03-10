package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class User {

    private String email;
    private String userId;
    private String password;
    private LocalDateTime createdAt;

    public User(String email, String userId, String password) {
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean hasSameEmail(String email) {
        return this.email.equals(email);
    }

    public boolean hasSameUserId(String userId) {
        return this.userId.equals(userId);
    }
}

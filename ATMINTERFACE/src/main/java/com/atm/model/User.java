package com.atm.model;

import java.util.Objects;

public class User {
    private String userId;
    private String pin;
    private String accountId;

    public User(String userId, String pin, String accountId) {
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.pin = Objects.requireNonNull(pin, "pin must not be null");
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

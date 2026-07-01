package com.atm.service;

import com.atm.constants.ATMConstants;
import com.atm.exception.AuthenticationException;
import com.atm.model.Account;
import com.atm.model.TransactionType;
import com.atm.model.User;
import com.atm.repository.BankRepository;
import com.atm.util.ValidationUtil;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private final BankRepository repository;
    private final Map<String, Integer> failedAttempts;
    private Account currentAccount;

    public AuthenticationService(BankRepository repository) {
        this.repository = repository;
        this.failedAttempts = new HashMap<>();
    }

    public Account login(String userId, String pin) {
        if (!ValidationUtil.isValidUserId(userId)) {
            throw new AuthenticationException("User ID is required and must be numeric.");
        }
        if (!ValidationUtil.isValidPin(pin)) {
            throw new AuthenticationException("PIN must be a 4-digit number.");
        }
        if (getFailedAttempts(userId) >= ATMConstants.MAX_LOGIN_ATTEMPTS) {
            throw new AuthenticationException("Session locked after 3 failed attempts.");
        }

        User user = repository.findUserById(userId).orElse(null);
        if (user == null || !user.getPin().equals(pin)) {
            registerFailedAttempt(userId);
            throw new AuthenticationException("Invalid credentials. Attempts remaining: " + getAttemptsRemaining(userId));
        }

        failedAttempts.remove(userId);
        currentAccount = repository.findAccountById(user.getAccountId())
                .orElseThrow(() -> new AuthenticationException("Linked account was not found."));
        return currentAccount;
    }

    public void logout(BankingService bankingService) {
        if (currentAccount != null) {
            bankingService.recordSystemTransaction(currentAccount, TransactionType.LOGOUT, "User logged out.");
        }
        currentAccount = null;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public boolean isLoggedIn() {
        return currentAccount != null;
    }

    public int getFailedAttempts(String userId) {
        return failedAttempts.getOrDefault(userId, 0);
    }

    public int getAttemptsRemaining(String userId) {
        return Math.max(0, ATMConstants.MAX_LOGIN_ATTEMPTS - getFailedAttempts(userId));
    }

    private void registerFailedAttempt(String userId) {
        failedAttempts.put(userId, getFailedAttempts(userId) + 1);
    }
}

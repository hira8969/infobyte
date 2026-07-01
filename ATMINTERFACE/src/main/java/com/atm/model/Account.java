package com.atm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {
    private String accountId;
    private String accountHolderName;
    private BigDecimal balance;
    private String pin;
    private final List<Transaction> transactionHistory;

    public Account(String accountId, String accountHolderName, BigDecimal balance, String pin) {
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
        this.accountHolderName = Objects.requireNonNull(accountHolderName, "accountHolderName must not be null");
        this.balance = Objects.requireNonNull(balance, "balance must not be null");
        this.pin = Objects.requireNonNull(pin, "pin must not be null");
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void transfer(Account recipient, BigDecimal amount) {
        withdraw(amount);
        recipient.deposit(amount);
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}

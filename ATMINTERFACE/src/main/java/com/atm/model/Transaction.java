package com.atm.model;

import com.atm.constants.ATMConstants;
import com.atm.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private String transactionId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;
    private BigDecimal balanceAfterTransaction;

    public Transaction(String transactionId, TransactionType transactionType, BigDecimal amount,
                       LocalDateTime timestamp, String description, BigDecimal balanceAfterTransaction) {
        this.transactionId = Objects.requireNonNull(transactionId, "transactionId must not be null");
        this.transactionType = Objects.requireNonNull(transactionType, "transactionType must not be null");
        this.amount = Objects.requireNonNull(amount, "amount must not be null");
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.balanceAfterTransaction = Objects.requireNonNull(balanceAfterTransaction,
                "balanceAfterTransaction must not be null");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s %.2f | %s | Balance: %s %.2f | %s",
                transactionId,
                transactionType.getDisplayName(),
                ATMConstants.CURRENCY_SYMBOL,
                amount,
                DateTimeUtil.format(timestamp),
                ATMConstants.CURRENCY_SYMBOL,
                balanceAfterTransaction,
                description);
    }
}

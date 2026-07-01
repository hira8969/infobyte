package com.atm.service;

import com.atm.exception.BankingException;
import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.model.TransactionType;
import com.atm.repository.BankRepository;
import com.atm.util.ValidationUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BankingService {
    private final BankRepository repository;
    private final AtomicInteger transactionSequence;

    public BankingService(BankRepository repository) {
        this.repository = repository;
        this.transactionSequence = new AtomicInteger(1);
    }

    public Transaction deposit(Account account, BigDecimal amount) {
        requireAccount(account);
        requirePositiveAmount(amount);
        account.deposit(amount);
        return recordTransaction(account, TransactionType.DEPOSIT, amount,
                "Deposit successful.");
    }

    public Transaction withdraw(Account account, BigDecimal amount) {
        requireAccount(account);
        requirePositiveAmount(amount);
        ensureSufficientBalance(account, amount);
        account.withdraw(amount);
        return recordTransaction(account, TransactionType.WITHDRAW, amount,
                "Withdrawal successful.");
    }

    public Transaction transfer(Account sender, String recipientAccountId, BigDecimal amount) {
        requireAccount(sender);
        requirePositiveAmount(amount);
        if (sender.getAccountId().equals(recipientAccountId)) {
            throw new BankingException("Cannot transfer money to the same account.");
        }

        Account recipient = repository.findAccountById(recipientAccountId)
                .orElseThrow(() -> new BankingException("Recipient account not found."));
        ensureSufficientBalance(sender, amount);

        sender.transfer(recipient, amount);
        Transaction senderTransaction = recordTransaction(sender, TransactionType.TRANSFER, amount,
                "Transferred to account " + recipient.getAccountId() + ".");
        recordTransaction(recipient, TransactionType.TRANSFER, amount,
                "Received from account " + sender.getAccountId() + ".");
        return senderTransaction;
    }

    public BigDecimal getBalance(Account account) {
        requireAccount(account);
        recordTransaction(account, TransactionType.BALANCE_CHECK, BigDecimal.ZERO, "Balance inquiry.");
        return account.getBalance();
    }

    public List<Transaction> getTransactionHistory(Account account) {
        requireAccount(account);
        return account.getTransactionHistory();
    }

    public Transaction getLastTransaction(Account account) {
        requireAccount(account);
        List<Transaction> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }

    public void recordSystemTransaction(Account account, TransactionType type, String description) {
        requireAccount(account);
        recordTransaction(account, type, BigDecimal.ZERO, description);
    }

    private Transaction recordTransaction(Account account, TransactionType type, BigDecimal amount, String description) {
        Transaction transaction = new Transaction(nextTransactionId(), type, amount, LocalDateTime.now(),
                description, account.getBalance());
        account.addTransaction(transaction);
        return transaction;
    }

    private String nextTransactionId() {
        return String.format("TXN%03d", transactionSequence.getAndIncrement());
    }

    private void requireAccount(Account account) {
        if (account == null) {
            throw new BankingException("Account is required.");
        }
    }

    private void requirePositiveAmount(BigDecimal amount) {
        if (!ValidationUtil.isPositiveAmount(amount)) {
            throw new BankingException("Amount must be greater than zero.");
        }
    }

    private void ensureSufficientBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new BankingException("Insufficient funds.");
        }
    }
}

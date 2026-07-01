package com.atm.service;

import com.atm.exception.BankingException;
import com.atm.model.Account;
import com.atm.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankingServiceTest {
    private BankRepository repository;
    private BankingService bankingService;
    private Account account;

    @BeforeEach
    void setUp() {
        repository = BankRepository.createWithSampleData();
        bankingService = new BankingService(repository);
        account = repository.findAccountById("1001").orElseThrow();
    }

    @Test
    void depositSuccess() {
        bankingService.deposit(account, new BigDecimal("5000"));
        assertEquals(new BigDecimal("55000.00"), account.getBalance());
    }

    @Test
    void depositInvalidAmount() {
        assertThrows(BankingException.class, () -> bankingService.deposit(account, BigDecimal.ZERO));
    }

    @Test
    void withdrawSuccess() {
        bankingService.withdraw(account, new BigDecimal("10000"));
        assertEquals(new BigDecimal("40000.00"), account.getBalance());
    }

    @Test
    void withdrawInsufficientFunds() {
        assertThrows(BankingException.class, () -> bankingService.withdraw(account, new BigDecimal("999999")));
    }

    @Test
    void transferSuccess() {
        Account recipient = repository.findAccountById("1002").orElseThrow();
        bankingService.transfer(account, recipient.getAccountId(), new BigDecimal("5000"));
        assertEquals(new BigDecimal("45000.00"), account.getBalance());
        assertEquals(new BigDecimal("35000.00"), recipient.getBalance());
    }

    @Test
    void transferInvalidAccount() {
        assertThrows(BankingException.class, () -> bankingService.transfer(account, "9999", new BigDecimal("5000")));
    }

    @Test
    void transferInsufficientFunds() {
        assertThrows(BankingException.class, () -> bankingService.transfer(account, "1002", new BigDecimal("999999")));
    }

    @Test
    void balanceInquiryRecordsTransaction() {
        assertEquals(new BigDecimal("50000.00"), bankingService.getBalance(account));
        assertFalse(account.getTransactionHistory().isEmpty());
    }

    @Test
    void transactionHistoryContainsOperations() {
        bankingService.deposit(account, new BigDecimal("100"));
        bankingService.withdraw(account, new BigDecimal("50"));
        assertEquals(2, bankingService.getTransactionHistory(account).size());
    }
}

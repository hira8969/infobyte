package com.atm.controller;

import com.atm.exception.AuthenticationException;
import com.atm.exception.BankingException;
import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.model.TransactionType;
import com.atm.service.AuthenticationService;
import com.atm.service.BankingService;
import com.atm.view.ATMView;

import java.math.BigDecimal;

public class ATMController {
    private final AuthenticationService authenticationService;
    private final BankingService bankingService;
    private final ATMView view;
    private boolean running;

    public ATMController(AuthenticationService authenticationService, BankingService bankingService, ATMView view) {
        this.authenticationService = authenticationService;
        this.bankingService = bankingService;
        this.view = view;
        this.running = true;
    }

    public void start() {
        view.showWelcome();
        while (running) {
            try {
                Account account = login();
                if (account != null) {
                    showMainMenu(account);
                }
            } catch (RuntimeException exception) {
                view.showError("Unexpected error: " + exception.getMessage());
            }
        }
    }

    private Account login() {
        while (running && !authenticationService.isLoggedIn()) {
            String userId = view.promptUserId();
            String pin = view.promptPin();
            try {
                Account account = authenticationService.login(userId, pin);
                bankingService.recordSystemTransaction(account, TransactionType.LOGIN, "User logged in.");
                view.showLoginSuccess(account);
                return account;
            } catch (AuthenticationException exception) {
                view.showError(exception.getMessage());
                if (authenticationService.getAttemptsRemaining(userId) == 0) {
                    running = false;
                }
            }
        }
        return authenticationService.getCurrentAccount();
    }

    private void showMainMenu(Account account) {
        while (running && authenticationService.isLoggedIn()) {
            int choice = view.promptMenuChoice();
            try {
                handleMenuChoice(choice, account);
            } catch (BankingException exception) {
                view.showError(exception.getMessage());
            }
        }
    }

    private void handleMenuChoice(int choice, Account account) {
        switch (choice) {
            case 1 -> view.showTransactionHistory(bankingService.getTransactionHistory(account));
            case 2 -> withdraw(account);
            case 3 -> deposit(account);
            case 4 -> transfer(account);
            case 5 -> balanceInquiry(account);
            case 6 -> view.showAccountInformation(account);
            case 7 -> logout();
            case 8 -> exit();
            default -> view.showError("Invalid menu choice. Please select 1 to 8.");
        }
    }

    private void deposit(Account account) {
        BigDecimal amount = view.promptAmount("Enter Deposit Amount");
        bankingService.deposit(account, amount);
        view.showSuccess("Deposit Successful. Current Balance: " + view.formatAmount(account.getBalance()));
    }

    private void withdraw(Account account) {
        BigDecimal amount = view.promptAmount("Enter Withdrawal Amount");
        bankingService.withdraw(account, amount);
        view.showSuccess("Withdrawal Successful. Current Balance: " + view.formatAmount(account.getBalance()));
    }

    private void transfer(Account account) {
        String recipientAccountId = view.promptRecipientAccountId();
        BigDecimal amount = view.promptAmount("Enter Transfer Amount");
        bankingService.transfer(account, recipientAccountId, amount);
        view.showSuccess("Transfer Successful. Transferred " + view.formatAmount(amount)
                + " to Account " + recipientAccountId + ".");
    }

    private void balanceInquiry(Account account) {
        bankingService.getBalance(account);
        Transaction lastTransaction = bankingService.getLastTransaction(account);
        view.showBalance(account, lastTransaction);
    }

    private void logout() {
        authenticationService.logout(bankingService);
        view.showMessage("You have been logged out successfully.");
    }

    private void exit() {
        if (authenticationService.isLoggedIn()) {
            authenticationService.logout(bankingService);
        }
        view.showMessage("Thank you for using ATM Interface System.");
        view.showMessage("Goodbye!");
        running = false;
    }
}

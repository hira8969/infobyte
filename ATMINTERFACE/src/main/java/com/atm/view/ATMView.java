package com.atm.view;

import com.atm.constants.ATMConstants;
import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.util.DateTimeUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ATMView {
    private final Scanner scanner;

    public ATMView() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Welcome to " + ATMConstants.APPLICATION_NAME);
        showLine();
    }

    public String promptUserId() {
        System.out.print("Enter User ID: ");
        return scanner.nextLine().trim();
    }

    public String promptPin() {
        System.out.print("Enter PIN: ");
        return scanner.nextLine().trim();
    }

    public int promptMenuChoice() {
        showLine();
        System.out.println("ATM MENU");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Balance Inquiry");
        System.out.println("6. Account Information");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        showLine();
        System.out.print("Enter Choice: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    public BigDecimal promptAmount(String label) {
        System.out.print(label + ": ");
        try {
            return new BigDecimal(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return BigDecimal.ZERO;
        }
    }

    public String promptRecipientAccountId() {
        System.out.print("Enter Recipient Account ID: ");
        return scanner.nextLine().trim();
    }

    public void showLoginSuccess(Account account) {
        showSuccess("Login Successful. Welcome, " + account.getAccountHolderName() + ".");
    }

    public void showTransactionHistory(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            showMessage("No transactions found.");
            return;
        }
        showLine();
        System.out.println("TRANSACTION HISTORY");
        showLine();
        System.out.printf("%-8s %-15s %-12s %-20s %-14s %s%n",
                "ID", "Type", "Amount", "Date Time", "Balance", "Description");
        for (Transaction transaction : transactions) {
            System.out.printf("%-8s %-15s %s %-9.2f %-20s %s %-10.2f %s%n",
                    transaction.getTransactionId(),
                    transaction.getTransactionType().getDisplayName(),
                    ATMConstants.CURRENCY_SYMBOL,
                    transaction.getAmount(),
                    DateTimeUtil.format(transaction.getTimestamp()),
                    ATMConstants.CURRENCY_SYMBOL,
                    transaction.getBalanceAfterTransaction(),
                    transaction.getDescription());
        }
    }

    public void showBalance(Account account, Transaction lastTransaction) {
        showLine();
        System.out.println("Balance: " + formatAmount(account.getBalance()));
        System.out.println("Available Balance: " + formatAmount(account.getBalance()));
        System.out.println("Last Transaction: " + (lastTransaction == null ? "No transaction yet" : lastTransaction));
    }

    public void showAccountInformation(Account account) {
        showLine();
        System.out.println("ACCOUNT INFORMATION");
        showLine();
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Account Holder Name: " + account.getAccountHolderName());
        System.out.println("Current Balance: " + formatAmount(account.getBalance()));
        System.out.println("Total Transactions: " + account.getTransactionHistory().size());
    }

    public void showSuccess(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String formatAmount(BigDecimal amount) {
        return String.format("%s %.2f", ATMConstants.CURRENCY_SYMBOL, amount);
    }

    private void showLine() {
        System.out.println("=================================");
    }
}

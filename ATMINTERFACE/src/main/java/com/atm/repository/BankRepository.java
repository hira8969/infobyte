package com.atm.repository;

import com.atm.model.Account;
import com.atm.model.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankRepository {
    private final Map<String, Account> accounts;
    private final Map<String, User> users;

    public BankRepository() {
        this.accounts = new HashMap<>();
        this.users = new HashMap<>();
    }

    public static BankRepository createWithSampleData() {
        BankRepository repository = new BankRepository();
        repository.saveAccount(new Account("1001", "Hiralal Kumar", new BigDecimal("50000.00"), "1234"));
        repository.saveAccount(new Account("1002", "Rahul Sharma", new BigDecimal("30000.00"), "4321"));
        repository.saveAccount(new Account("1003", "Priya Singh", new BigDecimal("45000.00"), "5678"));

        repository.saveUser(new User("1001", "1234", "1001"));
        repository.saveUser(new User("1002", "4321", "1002"));
        repository.saveUser(new User("1003", "5678", "1003"));
        return repository;
    }

    public void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    public Optional<Account> findAccountById(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    public Optional<User> findUserById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public Collection<Account> findAllAccounts() {
        return accounts.values();
    }
}

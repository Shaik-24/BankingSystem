package com.bank.BankingSystem.service;

import com.bank.BankingSystem.entity.Account;

public interface AccountService {

    Account createAccount(double initialBalance);

    Account deposit(Long accountId, double amount);

    Account withdraw(Long accountId, double amount);

    void transfer(Long senderId, Long receiverId, double amount);

    Account getAccount(Long accountId);
}

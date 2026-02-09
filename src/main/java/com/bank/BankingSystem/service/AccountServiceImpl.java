package com.bank.BankingSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bank.BankingSystem.entity.Account;
import com.bank.BankingSystem.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account createAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }
        return accountRepository.save(new Account(initialBalance));
    }


    public Account deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }

        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }


    public Account withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    @Transactional
    public void transfer(Long senderId, Long receiverId, double amount) {

        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (amount <= 0) {
            throw new RuntimeException("Transfer amount must be positive");
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}

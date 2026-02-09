package com.bank.BankingSystem.controller;

import org.springframework.web.bind.annotation.*;

import com.bank.BankingSystem.entity.Account;
import com.bank.BankingSystem.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 1️⃣ Create Account
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account.getBalance());
    }


    // 2️⃣ Deposit Money
    @PostMapping("/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestParam double amount) {

        return accountService.deposit(id, amount);
    }

    // 3️⃣ Withdraw Money
    @PostMapping("/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestParam double amount) {

        return accountService.withdraw(id, amount);
    }

    // 4️⃣ Transfer Money
    @PostMapping("/transfer")
    public String transfer(
            @RequestParam Long from,
            @RequestParam Long to,
            @RequestParam double amount) {

        accountService.transfer(from, to, amount);
        return "Transfer successful";
    }

    // 5️⃣ Get Account Details
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }
}

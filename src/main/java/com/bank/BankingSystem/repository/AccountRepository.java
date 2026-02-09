package com.bank.BankingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.BankingSystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

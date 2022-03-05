package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = accountRepository.getById(id);

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.subtract(money);

        if (newBalance.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("Money can't be negative.");
        }

        account.setBalance(newBalance);
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = accountRepository.getById(id);

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(money);

        if (money.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("Can't deposit negative sum");
        }

        account.setBalance(newBalance);
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.save(account);
    }
}

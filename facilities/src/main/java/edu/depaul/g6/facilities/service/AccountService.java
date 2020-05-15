package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Account;
import edu.depaul.g6.facilities.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.accountRepository = repository;
    }

    public void saveAccount(Account a) {
        accountRepository.save(a);
    }

}

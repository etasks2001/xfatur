package com.xfatur.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.test.BankAccount;
import com.xfatur.repository.test.BankAccountRepository;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository repository;

    @Transactional
    public void transfer(int amount) {
	BankAccount account1 = repository.findById(1).get();
	account1.setBal(account1.getBal() - amount);
	repository.save(account1);

	BankAccount account2 = repository.findById(2).get();
	account2.setBal(account2.getBal() + amount);
	repository.save(account2);
    }
}

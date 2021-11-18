package com.xfatur.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xfatur.model.test.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

}

package com.hiro.shi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiro.shi.domain.model.Account;
import com.hiro.shi.domain.repository.AccountRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	AccountRepository accountRepo;

	public Account save(Account account) {
		return accountRepo.save(account);
	}
	
	public boolean findAccount(Account account) {
		Account exitsAccount = accountRepo.findAccount(account.getName(), account.getPassword());
		
		return exitsAccount != null;
	}
}

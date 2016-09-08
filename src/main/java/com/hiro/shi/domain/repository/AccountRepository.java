package com.hiro.shi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hiro.shi.domain.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	@Query("select a from Account a where a.name = :name and password = :password")
	public Account findAccount(@Param("name") String name, @Param("password") String password);
}

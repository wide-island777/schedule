package com.hiro.shi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hiro.shi.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.name = :name and password = :password")
	public User findUser(@Param("name") String name, @Param("password") String password);
}

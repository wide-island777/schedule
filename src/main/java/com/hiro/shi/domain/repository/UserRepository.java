package com.hiro.shi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiro.shi.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByusername(String username);
}

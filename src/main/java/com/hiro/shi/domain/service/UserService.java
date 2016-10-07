package com.hiro.shi.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiro.shi.domain.model.User;
import com.hiro.shi.domain.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User save(User user) {
		return userRepo.save(user);
	}

	public List<User> findUserAll() {
		return userRepo.findAll();
	}

}

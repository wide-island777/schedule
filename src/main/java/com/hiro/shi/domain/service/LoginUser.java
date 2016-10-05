package com.hiro.shi.domain.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.hiro.shi.domain.model.User;

public class LoginUser extends org.springframework.security.core.userdetails.User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	public LoginUser(User user) {
		super(user.getUsername(), user.getPassword(),
					AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}

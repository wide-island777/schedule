package com.hiro.shi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hiro.shi.domain.service.UserDetailsServiceImpl;

public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
	// ユーザ情報を取得するサービス
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	// パスワードの暗号化方式
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		// 認証するユーザを設定する
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}

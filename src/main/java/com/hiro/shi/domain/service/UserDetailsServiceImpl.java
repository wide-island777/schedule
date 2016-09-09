package com.hiro.shi.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hiro.shi.domain.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 認証を行うユーザ情報を格納する
		User user = null;
		try {
			// 入力したユーザIDから認証を行うユーザ情報を取得する
			// 処理内容は省略
		} catch (Exception e) {
			// 取得時にExceptionが発生した場合
			throw new UsernameNotFoundException("ユーザ情報取得時にエラーが発生しました。");
		}
		
		if (user == null) {
			throw new UsernameNotFoundException("ユーザ情報が取得できませんでした。");
		}
		
		// ユーザ情報が取得できたらSpring Securityで認証できる形で戻す→Userクラスもしくはそれを継承したクラス
		return new LoginUser(user);
	}

}

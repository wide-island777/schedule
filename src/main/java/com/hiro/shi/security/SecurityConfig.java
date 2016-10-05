package com.hiro.shi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hiro.shi.domain.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity webSecurity) {
		// セキュリティ設定を無視するリクエスト設定
		// 静的リソース(images, css, javascript)に対するアクセスはセキュリティ設定を無視する
		webSecurity.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 認可の設定
		http.authorizeRequests().antMatchers("/", "/login", "/login/auth", "/login/entryForm", "/login/save")
				.permitAll() // loginは全ユーザアクセス許可
				.anyRequest().authenticated(); // それ以外はすべて認証無しのアクセスは不許可

		// ログイン設定
		http.csrf().disable().formLogin().loginProcessingUrl("/login/auth") // 認証処理のパス
				.loginPage("/login") // ログインフォームのパス
				.failureHandler(new OriginalAuthenticationFailureHandler()) // 認証失敗時に呼ばれるハンドラクラス
				.defaultSuccessUrl("/top")
				.usernameParameter("name").passwordParameter("password") // ユーザ名、パスワードのパラメータ名
				.and();

		// ログアウト設定
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/login");
	}

	@Autowired
	private UserDetailsServiceImpl userService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());;
	}
	
	
}

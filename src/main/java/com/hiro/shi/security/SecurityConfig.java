package com.hiro.shi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hiro.shi.domain.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity webSecurity){
		// セキュリティ設定を無視するリクエスト設定
		// 静的リソース(images, css, javascript)に対するアクセスはセキュリティ設定を無視する
		webSecurity.ignoring().antMatchers("/css/**","/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 認可の設定
		http.authorizeRequests()
			.antMatchers("/", "/login", "/login/auth", "/login/entryForm", "/login/save").permitAll() // loginは全ユーザアクセス許可
			.anyRequest().authenticated();	// それ以外はすべて認証無しのアクセスは不許可
		
		// ログイン設定
		http.csrf().disable().formLogin()
			.loginProcessingUrl("/login") // 認証処理のパス
			.loginPage("/login/auth") // ログインフォームのパス
			.failureHandler(new OriginalAuthenticationFailureHandler()) // 認証失敗時に呼ばれるハンドラクラス
			.defaultSuccessUrl("/top")
			.usernameParameter("login_id").passwordParameter("login_password") // ユーザ名、パスワードのパラメータ名
			.and();
		
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
			.logoutSuccessUrl("/login");
	}
	
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		UserDetailsServiceImpl userDetailsService;
		
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			// 認証するユーザを設定する
			auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}
	}
	
	
}

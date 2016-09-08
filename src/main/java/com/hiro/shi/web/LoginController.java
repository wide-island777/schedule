package com.hiro.shi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiro.shi.domain.model.Account;
import com.hiro.shi.domain.service.AccountService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loginForm() {

		return "login";
	}

	@RequestMapping(value = "entryForm", method = RequestMethod.GET)
	public String entryForm() {

		return "entryForm";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
		Account account = new Account();
		account.setName(name);
		account.setPassword(password);

		accountService.save(account);

		return "login";
	}
	
	@RequestMapping(value = "auth", method = RequestMethod.GET)
	public String auth(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
		Account account = new Account();
		account.setName(name);
		account.setPassword(password);
		
		boolean loginSucess = accountService.findAccount(account);
		
		return loginSucess ? "top" : "login";
	}
}

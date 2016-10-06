package com.hiro.shi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hiro.shi.domain.model.User;
import com.hiro.shi.domain.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

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
		User entryUser = new User();
		entryUser.setUsername(name);
		entryUser.setPassword(new BCryptPasswordEncoder().encode(password));
		User user = userService.save(entryUser);
		logger.info("entryUserName : " + user.getUsername());
		logger.info("entryUserPass : " + user.getPassword());

		return "redirect:login";
	}

	@RequestMapping(value = "auth", method = RequestMethod.POST)
	public void auth() {
	}
}

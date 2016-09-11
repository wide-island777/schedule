package com.hiro.shi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/top")
public class TopContoller {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String top() {
		return "top";
	}
}

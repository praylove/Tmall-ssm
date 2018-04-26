package com.sherl.tmall.web.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForeController {

	@RequestMapping(value = { "", "/index" })
	public String index() {
		return "fore/index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "fore/login";
	}

	@RequestMapping(value = "/register")
	public String register() {
		return "fore/register";
	}

}

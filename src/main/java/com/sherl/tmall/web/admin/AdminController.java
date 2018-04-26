package com.sherl.tmall.web.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminIndex() {
		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login() {
		return "/admin/login";
	}

	@RequestMapping(value = "/admin/adminname", method = RequestMethod.GET)
	@ResponseBody
	public String getAdminName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

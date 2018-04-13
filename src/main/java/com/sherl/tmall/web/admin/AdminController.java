package com.sherl.tmall.web.admin;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sherl.tmall.service.AdminAccountService;

@Controller
public class AdminController {

	@Autowired
	private AdminAccountService accountService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminIndex() {
		return "redirect:/admin/category";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login() {
		return "/admin/login";
	}

	@RequestMapping(value = "/admin/nameValidate", method = RequestMethod.POST)
	@ResponseBody
	public boolean isNonExist(@RequestParam String adminname) {
		return accountService.isExist(adminname);
	}

	@RequestMapping(value = "/admin/passwordValidate", method = RequestMethod.POST)
	@ResponseBody
	public boolean isErrorPassword(@RequestParam String adminname, @RequestParam String password) {
		boolean status = false;
		try {
			status = accountService.isRightPassword(adminname, password);
		} catch (NoSuchAlgorithmException e) {
			status = false;
		}

		return status;
	}

	@RequestMapping(value = "/admin/adminname", method = RequestMethod.GET)
	@ResponseBody
	public String getAdminName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

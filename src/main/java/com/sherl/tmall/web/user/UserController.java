package com.sherl.tmall.web.user;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.UserMapper;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/admin/user")
	public String userIndex() {
		return "/admin/user";
	}

	@RequestMapping(value = "/user/username", method = RequestMethod.GET)
	@ResponseBody
	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "";
		}
		return authentication.getName();
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<User> list(@RequestParam(required = false) Integer p) {
		if (p == null)
			p = 1;
		return userService.list(p, 5);
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User get(@PathVariable int id) {
		return userService.get(id);
	}

	@RequestMapping(value = "/nameValidate", method = RequestMethod.POST)
	@ResponseBody
	public boolean isExist(@RequestParam String name) {
		return userService.isExist(name);
	}

	@RequestMapping(value = "/nameExistValidate", method = RequestMethod.POST)
	@ResponseBody
	public boolean isNonExist(@RequestParam String name) {
		return !userService.isExist(name);
	}

	@RequestMapping(value = "/passwordValidate", method = RequestMethod.POST)
	@ResponseBody
	public boolean isErrorPassword(@RequestParam String name, @RequestParam String password) {
		boolean status = false;
		try {
			status = userService.isRightPassword(name, password);
		} catch (NoSuchAlgorithmException e) {
			status = false;
		}

		return status;
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		userService.delete(id);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public String register(@Valid UserForm form, BindingResult result) {
		if (result.hasErrors())
			return "input error";
		if (!form.getPassword1().equals(form.getPassword2())) {
			System.out.println("输入密码不一致！");
			return "input error";
		}
		User user = new User();
		user.setName(form.getName());
		user.setPassword(form.getPassword1());
		user.setRole(UserMapper.USER);
		try {
			userService.add(user);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("add error");
			return "service error";
		}

		return "success";
	}
}

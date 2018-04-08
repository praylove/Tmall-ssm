package com.sherl.tmall.web.user;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping("/admin/user")
	public String userIndex() {
		return "/admin/user";
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<User> list(@RequestParam(required = false) Integer p, Model model) {
		if (p == null)
			p = 1;
		return userService.list(p, 5);
	}

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User get(@PathVariable int id) {
		return userService.get(id);
	}

	// @RequestMapping(value = "/users", method = RequestMethod.POST)
	// @ResponseBody
	// public void add(@Valid UserForm form, @PathVariable int cid) {
	//
	// }

	@RequestMapping(value = "/admin/users/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		userService.delete(id);
	}
}

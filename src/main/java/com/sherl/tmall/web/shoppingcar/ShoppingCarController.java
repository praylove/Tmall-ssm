package com.sherl.tmall.web.shoppingcar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sherl.tmall.entity.User;
import com.sherl.tmall.entity.UserDetailsImpl;
import com.sherl.tmall.service.ProductService;
import com.sherl.tmall.service.ShoppingCarService;

@Controller
public class ShoppingCarController {

	@Autowired
	private ShoppingCarService carService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/shoppingcar/count", method = RequestMethod.GET)
	@ResponseBody
	public int getCount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return 0;
		}
		Object o = authentication.getPrincipal();
		if (o instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) o).getUser();
			return carService.count(user.getId());
		}
		return 0;
	}

	@RequestMapping(value = "/shoppingcars", method = RequestMethod.POST)
	@ResponseBody
	public String add(@Valid ShoppingCarForm form, BindingResult result) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (result.hasErrors()) {
			return "valide error";
		}
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "notLogin";
		}
		Object o = authentication.getPrincipal();
		if (o instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) o).getUser();
			carService.carAddOperation(user.getId(), form.getPid(), form.getNumber());
			return "success";
		}
		System.out.println(o);
		return "error";
	}

	// @RequestMapping(value = "", method = RequestMethod.POST)
	// public String list() {
	//
	// }

}

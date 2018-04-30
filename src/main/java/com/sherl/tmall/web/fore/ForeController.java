package com.sherl.tmall.web.fore;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.entity.UserDetailsImpl;
import com.sherl.tmall.service.ProductService;
import com.sherl.tmall.service.ShoppingCarService;
import com.sherl.tmall.web.shoppingcar.ShoppingCarForm;

@Controller
public class ForeController {

	@Autowired
	private ShoppingCarService carService;

	@Autowired
	private ProductService productService;

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

	@RequestMapping(value = "/s-shoppingcar")
	public String shoppingCar() {
		return "fore/shoppingCart";
	}

	@RequestMapping(value = "/s-buy-car")
	public String createOrderByCar(@RequestParam Integer[] carids, HttpSession session, Model model) {
		List<OrderItem> ois = carService.listByArray(carids);
		int totalPrice = 0;
		for (OrderItem oi : ois) {
			totalPrice += oi.getPrices();
		}
		session.setAttribute("ois", ois);
		model.addAttribute("total", totalPrice);

		return "fore/createOrder";
	}

	@RequestMapping(value = "/s-buy")
	public String createOrder(@Valid ShoppingCarForm form, BindingResult results, HttpSession session, Model model) {
		if (results.hasErrors()) {
			return "redirect: /";
		}
		List<OrderItem> ois = new ArrayList<>();
		OrderItem oi = new OrderItem();
		oi.setId(0);
		oi.setNumber(form.getNumber());
		oi.setProduct(productService.get(form.getPid()));
		oi.setPrices(oi.getNumber() * oi.getProduct().getPromotePrice());
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(o instanceof UserDetailsImpl)) {
			return "redirect:/";
		}
		User user = ((UserDetailsImpl) o).getUser();
		oi.setUser(user);
		ois.add(oi);
		session.setAttribute("ois", ois);
		model.addAttribute("total", oi.getPrices());

		return "fore/createOrder";
	}

	@RequestMapping(value = "/s-order/list")
	public String orderList() {
		return "fore/order";
	}
}

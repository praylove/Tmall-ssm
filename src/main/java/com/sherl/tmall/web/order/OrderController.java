package com.sherl.tmall.web.order;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.entity.UserDetailsImpl;
import com.sherl.tmall.service.OrderItemService;
import com.sherl.tmall.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService itemService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping("/admin/order")
	public String orderIndex() {
		return "admin/order";
	}

	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Order> list(@RequestParam(required = false) Integer p) {
		if (p == null) {
			p = 1;
		}
		return orderService.list(p, 5);
	}

	@RequestMapping(value = "/admin/orders/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Order get(@PathVariable int id) {
		return orderService.get(id);
	}

	@RequestMapping(value = "/admin/orders/{id}/delivery", method = RequestMethod.POST)
	@ResponseBody
	public void delivery(@PathVariable int id) {
		orderService.delivery(id);
	}

	@RequestMapping(value = "/admin/orders/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		orderService.delete(id);
		itemService.deleteByOid(id);
	}

	@RequestMapping(value = "/s-orders", method = RequestMethod.GET)
	@ResponseBody
	public List<Order> list() {
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(o instanceof UserDetailsImpl)) {
			return null;
		}
		User user = ((UserDetailsImpl) o).getUser();
		List<Order> orders = orderService.list(user.getId());
		for (Order order : orders) {
			order.setOrderItems(itemService.list(order.getId()));
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/s-order/create")
	public String create(@Valid OrderForm form, BindingResult results, HttpSession session) {
		Order order = beanMapper.map(form, Order.class);
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(o instanceof UserDetailsImpl)) {
			return "redirect:/";
		}
		User user = ((UserDetailsImpl) o).getUser();
		order.setUser(user);
		orderService.create(order);
		List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
		itemService.addArray(ois, order.getId());

		return "redirect:/s-order/" + order.getId() + "/pay";
	}

	@RequestMapping(value = "/s-order/{id}/pay")
	public String pay(@PathVariable int id, Model model) {
		model.addAttribute("total", itemService.getTotalPrice(id));
		model.addAttribute("oid", id);
		return "fore/pay";
	}

	@RequestMapping(value = "/s-order/{id}/pay-sussess")
	public String paySussess(@PathVariable int id, Model model) {
		orderService.pay(id);
		model.addAttribute("total", itemService.getTotalPrice(id));
		model.addAttribute("time", orderService.get(id).getPayDate());
		return "fore/paySussess";
	}

	@RequestMapping(value = "/s-order/{id}/confirm")
	public String confirm(@PathVariable int id, Model model) {
		Order o = orderService.get(id);
		o.setOrderItems(itemService.list(id));
		model.addAttribute("o", o);
		return "fore/confirm";
	}

	@RequestMapping(value = "/s-order/{id}/confirm-success")
	public String confirmSuccess(@PathVariable int id, Model model) {
		orderService.confirm(id);
		return "fore/confirmSuccess";
	}

	@RequestMapping(value = "/s-order/{id}/review")
	public String review(@PathVariable int id, Model model) {
		Order o = orderService.get(id);
		o.setOrderItems(itemService.list(id));
		model.addAttribute("o", o);
		return "fore/review";
	}

	@RequestMapping(value = "/s-order/{id}/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void userDelete(@PathVariable int id) {
		orderService.delete(id);
		itemService.deleteByOid(id);
	}
}

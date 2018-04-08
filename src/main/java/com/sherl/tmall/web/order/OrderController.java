package com.sherl.tmall.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

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
	}
}

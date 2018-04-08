package com.sherl.tmall.web.orderItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.service.OrderItemService;
import com.sherl.tmall.service.OrderService;

@Controller
public class OrderItemController {

	@Autowired
	private OrderItemService itemService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/admin/orders/{oid}/items", method = RequestMethod.GET)
	@ResponseBody
	public List<OrderItem> list(@PathVariable int oid) {
		return itemService.list(oid);
	}

	@RequestMapping(value = "/admin/orders/{oid}/items/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void delete(@PathVariable int id) {
		itemService.delete(id);
	}
}

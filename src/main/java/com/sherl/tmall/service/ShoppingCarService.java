package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherl.tmall.dao.OrderItemMapper;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.OrderItem;

@Service
public class ShoppingCarService {

	@Autowired
	private OrderItemMapper mapper;

	@Autowired
	private UserService userSerivce;

	@Autowired
	private ProductService productService;

	public List<OrderItem> list(int uid) {
		return mapper.carList(uid);
	}

	public int count(int uid) {
		return mapper.carCount(uid);
	}

	public OrderItem get(int uid, int pid) {
		return mapper.getByProductAndUser(uid, pid);
	}

	public void carAddOperation(int uid, int pid, int number) {
		OrderItem oi = get(uid, pid);
		if (oi == null) {
			oi = new OrderItem();
			oi.setUser(userSerivce.get(uid));
			oi.setProduct(productService.get(pid));
			oi.setNumber(number);
			oi.setPrices(oi.getNumber() * oi.getProduct().getPromotePrice());
			addItem(oi);
		} else {
			oi.setNumber(number);
			oi.setPrices(oi.getNumber() * oi.getProduct().getPromotePrice());
			updateItem(oi);
		}
	}

	@Transactional
	public void addItem(OrderItem oi) {
		Order o = new Order();
		o.setId(0);
		oi.setOrder(o);
		mapper.add(oi);
	}

	@Transactional
	public void updateItem(OrderItem o) {
		mapper.update(o);
	}

	@Transactional
	public void increaseNumber(int id) {
		OrderItem oi = mapper.getById(id);
		if (oi == null || oi.getOrder().getId() != 0)
			return;
		oi.setNumber(oi.getNumber() + 1);
		updateItem(oi);
	}
}

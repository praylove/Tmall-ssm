package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherl.tmall.dao.OrderItemMapper;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.Product;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemMapper mapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductImageService imageService;

	public List<OrderItem> list(int oid) {
		List<OrderItem> ois = mapper.list(oid);
		for (OrderItem oi : ois) {
			Product p = oi.getProduct();
			p.setFirstProductImage(imageService.getFirstProductImage(p.getId()));
		}
		return ois;
	}

	public OrderItem get(int id) {
		return mapper.getById(id);
	}

	public int getTotalPrice(int oid) {
		List<OrderItem> ois = list(oid);
		int totalPrice = 0;
		for (OrderItem oi : ois) {
			totalPrice += oi.getPrices();
		}
		return totalPrice;
	}

	public void addArray(List<OrderItem> ois, int oid) {
		Order order = orderService.get(oid);
		for (OrderItem oi : ois) {
			oi.setOrder(order);

			if (oi.getId() == 0) {
				add(oi);
			} else {
				update(oi);
			}
		}
	}

	@Transactional
	public void add(OrderItem oi) {
		mapper.add(oi);
	}

	@Transactional
	public void update(OrderItem oi) {
		mapper.update(oi);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}

	@Transactional
	public void deleteByOid(int oid) {
		mapper.deleteByOid(oid);
	}
}

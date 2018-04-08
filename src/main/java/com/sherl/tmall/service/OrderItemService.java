package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sherl.tmall.dao.OrderItemMapper;
import com.sherl.tmall.entity.OrderItem;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemMapper mapper;

	public List<OrderItem> list(int oid) {
		return mapper.list(oid);
	}

	public OrderItem get(int id) {
		return mapper.getById(id);
	}

	public void add(OrderItem oi) {
		mapper.add(oi);
	}

	public void update(OrderItem oi) {
		mapper.update(oi);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}

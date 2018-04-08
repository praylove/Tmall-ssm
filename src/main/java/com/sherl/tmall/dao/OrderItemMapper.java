package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.OrderItem;

@Repository
public interface OrderItemMapper {

	public List<OrderItem> list(int oid);

	public OrderItem getById(int id);

	public void add(OrderItem oi);

	public void update(OrderItem oi);

	public void delete(int id);

}

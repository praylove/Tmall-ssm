package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Order;

@Repository
public interface OrderMapper {

	public Order getById(int id);

	public List<Order> list();

	public List<Order> listByUid(int uid);

	public void add(Order o);

	public void update(Order o);

	public void delete(int id);

	public void updateStatus(Order o);
}

package com.sherl.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.OrderItem;

@Repository
public interface OrderItemMapper {

	public List<OrderItem> list(int oid);

	public OrderItem getById(int id);

	public OrderItem getByProductAndUser(@Param("uid") int uid, @Param("pid") int pid);

	public OrderItem getCarById(int id);

	public int carCount(int uid);

	public List<OrderItem> carList(int uid);

	public void add(OrderItem oi);

	public void update(OrderItem oi);

	public void delete(int id);

	public void deleteByOid(int oid);
}

package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.ShoppingCar;

@Deprecated
@Repository
public interface ShoppingCarMapper {

	public List<ShoppingCar> listByUid(int uid);

	public int getCount(int uid);

	public void add(ShoppingCar car);

	public void update(ShoppingCar car);

	public void delete(int id);
}

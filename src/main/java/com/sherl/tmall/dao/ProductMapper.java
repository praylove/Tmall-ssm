package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Product;

@Repository
public interface ProductMapper {

	public List<Product> list(int cid);

	public Product getById(int id);

	public void add(Product p);

	public void update(Product p);

	public void delete(int id);
}

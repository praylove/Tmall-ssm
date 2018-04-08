package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Category;

@Repository
public interface CategoryMapper {

	// public int getTotal();

	public void add(Category c);

	public void update(Category c);

	public void delete(int id);

	public Category getById(int id);

	public List<Category> list();
}

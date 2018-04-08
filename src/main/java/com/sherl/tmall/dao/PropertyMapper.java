package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Property;

@Repository
public interface PropertyMapper {

	public void add(Property pp);

	public void update(Property pp);

	public void delete(int id);

	public Property getById(int id);

	public List<Property> list(int cid);
}

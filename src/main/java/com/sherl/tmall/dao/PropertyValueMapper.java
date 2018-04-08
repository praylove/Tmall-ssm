package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.PropertyValue;

@Repository
public interface PropertyValueMapper {

	public List<PropertyValue> list(int pid);

	public PropertyValue getById(int id);

	public void add(PropertyValue ppv);

	public void update(PropertyValue ppv);

	public void delete(int id);

}
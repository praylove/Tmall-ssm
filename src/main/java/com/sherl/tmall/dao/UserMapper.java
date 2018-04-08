package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.User;

@Repository
public interface UserMapper {

	public List<User> list();

	public User getById(int id);

	public User getByName(String name);

	public User getByNameAndPassword(String name, String password);

	public void add(User u);

	public void update(User u);

	public void delete(int id);

}

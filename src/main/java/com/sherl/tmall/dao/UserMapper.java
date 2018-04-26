package com.sherl.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.User;

@Repository
public interface UserMapper {

	public static final String USER = "ROLE_USER";

	public static final String ADMIN = "ROLE_ADMIN";

	public List<User> list();

	public User getById(int id);

	public User getByName(String name);

	public User getByNameAndPassword(@Param("name") String name, @Param("password") String password);

	public void add(User u);

	public void update(@Param("name") String name, @Param("password") String password);

	public void delete(int id);

}

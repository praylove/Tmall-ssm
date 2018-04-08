package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.UserMapper;
import com.sherl.tmall.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;

	public List<User> list() {
		return mapper.list();
	}

	public PageInfo<User> list(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		PageInfo<User> info = new PageInfo<>(mapper.list());
		return info;
	}

	public User get(int id) {
		return mapper.getById(id);
	}

	@Transactional
	public void add(User u) {
		mapper.add(u);
	}

	@Transactional
	public void update(User u) {
		mapper.update(u);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}
}

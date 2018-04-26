package com.sherl.tmall.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.UserMapper;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.util.EncoderUtil;

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

	public User get(String username) {
		return mapper.getByName(username);
	}

	public boolean isExist(String username) {
		return mapper.getByName(username) != null;
	}

	public boolean isRightPassword(String adminname, String password) throws NoSuchAlgorithmException {
		MessageDigest encoder = MessageDigest.getInstance("MD5");
		encoder.update(password.getBytes());
		String encodePassword = EncoderUtil.md5Encode(password);
		return mapper.getByNameAndPassword(adminname, encodePassword) != null;
	}

	@Transactional
	public void add(User u) throws NoSuchAlgorithmException {
		String password = u.getPassword();
		u.setPassword(EncoderUtil.md5Encode(password));
		mapper.add(u);
	}

	@Transactional
	public void update(String name, String password) {
		mapper.update(name, password);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}
}

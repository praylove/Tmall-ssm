package com.sherl.tmall.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sherl.tmall.dao.AdminAccountMapper;
import com.sherl.tmall.entity.AdminAccount;
import com.sherl.tmall.util.EncoderUtil;

@Service
public class AdminAccountService {

	@Autowired
	private AdminAccountMapper accountMapper;

	public AdminAccount get(String adminname) {
		return accountMapper.getByName(adminname);
	}

	public boolean isExist(String adminname) {
		return this.get(adminname) != null;
	}

	public boolean isRightPassword(String adminname, String password) throws NoSuchAlgorithmException {
		MessageDigest encoder = MessageDigest.getInstance("MD5");
		encoder.update(password.getBytes());
		String encodePassword = EncoderUtil.md5Encode(password);
		return accountMapper.getByNameAndPassword(adminname, encodePassword) != null;
	}
}

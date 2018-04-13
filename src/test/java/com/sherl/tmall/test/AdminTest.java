package com.sherl.tmall.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.AdminAccountMapper;
import com.sherl.tmall.entity.AdminAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class AdminTest {

	@Autowired
	private AdminAccountMapper mapper;

	@Test
	public void testDao() {
		AdminAccount admin = mapper.getByName("a");
		// System.out.println(admin);
		// List<AdminAccount> as = mapper.list();
		// System.out.println(as);
		String encode = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update("a".getBytes());
			encode = new BigInteger(1, md5.digest()).toString(16);
			if (encode.length() < 32) {
				encode = 0 + encode;
			}
			System.out.println(encode);
			System.out.println(admin.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		AdminAccount admin1 = mapper.getByNameAndPassword("a", encode);
		System.out.println(admin1);
	}

}

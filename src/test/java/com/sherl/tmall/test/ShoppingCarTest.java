package com.sherl.tmall.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.ShoppingCarMapper;
import com.sherl.tmall.service.ShoppingCarService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class ShoppingCarTest {

	@Autowired
	public ShoppingCarMapper carMapper;

	@Autowired
	private ShoppingCarService carService;

	@Test
	public void testDAO() {
		System.out.println(carMapper.listByUid(1).get(0).getProduct());
	}

	@Test
	public void testService() {
		System.out.println(carService == null);
		System.out.println(carService.get(22));
	}

}

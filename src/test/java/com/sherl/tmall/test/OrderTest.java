package com.sherl.tmall.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.OrderMapper;
import com.sherl.tmall.dao.UserMapper;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.Status;
import com.sherl.tmall.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class OrderTest {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderService orderService;

	@Test
	public void testDAO() {
		List<Order> os = orderMapper.list();
		for (Order o : os) {
			System.out.println(o.getId() + "---" + o.getStatus());
		}

		Order o = new Order();
		o.setStatus(Status.UNPAY);
		o.setCreateDate(new Date());
		o.setAddress("四川省成都市武侯区");
		o.setPost("610000");
		o.setMobile("12333334444");
		o.setReceiver("tttt");
		o.setUser(userMapper.getById(1));
		o.setOrderCode(o.codeGenerator());
		System.out.println(o.getOrderCode());
		orderMapper.add(o);
		assertEquals(orderMapper.getById(o.getId()).getAddress(), "四川省成都市武侯区");
		o.setAddress("四川省成都市双华区");
		orderMapper.update(o);
		assertEquals(orderMapper.getById(o.getId()).getAddress(), "四川省成都市双华区");
		o.setStatus(Status.UNDELIVER);
		orderMapper.updateStatus(o);
		assertEquals(orderMapper.getById(o.getId()).getStatus().getName(), "待发货");
		orderMapper.delete(o.getId());
		assertEquals(orderMapper.getById(o.getId()), null);
		System.out.println("test over!");
	}

	@Test
	public void testService() {
		Order o = orderService.get(22);
		System.out.println(o.getStatus());
		o.setStatus(Status.UNCONFIRM);
		orderService.update(o);
		orderService.delivery(22);
		o = orderService.get(22);
		System.out.println(o.getStatus());
	}
}

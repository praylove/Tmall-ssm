package com.sherl.tmall.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.OrderItemMapper;
import com.sherl.tmall.dao.OrderMapper;
import com.sherl.tmall.dao.ProductMapper;
import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class OrderItemTest {

	@Autowired
	private OrderItemMapper mapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void testDAO() {
		List<OrderItem> ois = mapper.list(13);
		for (OrderItem oi : ois) {
			System.out.println(oi);
		}
		OrderItem oi = new OrderItem();
		oi.setNumber(10);
		oi.setOrder(orderMapper.getById(13));
		Product p = productMapper.getById(6);
		oi.setPrices(p.getPromotePrice() * oi.getNumber());
		oi.setProduct(p);
		mapper.add(oi);
		assertEquals(mapper.getById(oi.getId()).getNumber(), 10);
		oi.setNumber(11);
		mapper.update(oi);
		assertEquals(mapper.getById(oi.getId()).getNumber(), 11);
		mapper.delete(oi.getId());
		assertEquals(mapper.getById(oi.getId()), null);
		System.out.println("test over!");
	}
}

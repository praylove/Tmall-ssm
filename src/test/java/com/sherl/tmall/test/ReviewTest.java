package com.sherl.tmall.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.ProductMapper;
import com.sherl.tmall.dao.ReviewMapper;
import com.sherl.tmall.dao.UserMapper;
import com.sherl.tmall.entity.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class ReviewTest {

	@Autowired
	private ReviewMapper mapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void testDAO() {
		List<Review> rs = mapper.list(5);
		for (Review r : rs) {
			System.out.println(r.getContent() + "---" + r.getUser().getName() + "----" + r.getReviewDate());
		}
		Review r = new Review();
		r.setContent("好评");
		r.setProduct(productMapper.getById(5));
		r.setUser(userMapper.getById(1));
		mapper.add(r);
		assertEquals(mapper.getById(r.getId()).getContent(), "好评");
		mapper.delete(r.getId());
		assertEquals(mapper.getById(r.getId()), null);
		System.out.println("test over!");
	}

}

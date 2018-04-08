package com.sherl.tmall.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.ProductImageMapper;
import com.sherl.tmall.dao.ProductMapper;
import com.sherl.tmall.entity.ProductImage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class ProductImageTest {

	@Autowired
	private ProductImageMapper imageMapper;

	@Autowired
	private ProductMapper mapper;

	@Test
	public void testDAO() {
		List<ProductImage> pis = imageMapper.list(5);
		for (ProductImage pi : pis) {
			System.out.println(pi);
		}
		ProductImage pi = new ProductImage();
		pi.setType("details");
		pi.setProduct(mapper.getById(6));
		imageMapper.add(pi);
		// imageMapper.delete(pi.getId());
		System.out.println("test successful!");
	}
}

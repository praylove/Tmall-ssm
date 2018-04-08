package com.sherl.tmall.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sherl.tmall.dao.CategoryMapper;
import com.sherl.tmall.dao.PropertyMapper;
import com.sherl.tmall.entity.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PropertyTest {

	@Autowired
	private PropertyMapper mapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void testDAO() {
		List<Property> ps = mapper.list(3);
		for (Property p : ps) {
			System.out.println(p.getId() + "--" + p.getName() + "--" + p.getCategory());
		}
		System.out.println("=================");
		Property p = mapper.getById(24);
		System.out.println(p.getId() + "--" + p.getName() + "--" + p.getCategory());
		System.out.println("=================");
		Property p1 = new Property();
		p1.setName("lalala");
		p1.setCategory(categoryMapper.getById(39));
		mapper.add(p1);
		List<Property> ps1 = mapper.list(39);
		for (Property p2 : ps1) {
			System.out.println(p2.getId() + "--" + p2.getName() + "--" + p2.getCategory());
		}
		assertEquals(ps1.get(0).getName(), "lalala");
		p1.setName("heheh");
		mapper.update(p1);
		assertEquals(mapper.list(39).get(0).getName(), "heheh");
		mapper.delete(p1.getId());

		assertEquals(mapper.list(39), new ArrayList<Property>());
		System.out.println("OVER!");
	}
}

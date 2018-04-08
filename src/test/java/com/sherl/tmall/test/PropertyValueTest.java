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
import com.sherl.tmall.dao.PropertyMapper;
import com.sherl.tmall.dao.PropertyValueMapper;
import com.sherl.tmall.entity.PropertyValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PropertyValueTest {

	@Autowired
	private PropertyValueMapper mapper;

	@Autowired
	private PropertyMapper propertyMapper;

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void testDAO() {
		List<PropertyValue> ppvs = mapper.list(5);
		for (PropertyValue ppv : ppvs) {
			System.out.println(ppv);
		}
		PropertyValue ppv = new PropertyValue();
		ppv.setProduct(productMapper.getById(6));
		ppv.setProperty(propertyMapper.getById(20));
		ppv.setValue("11111");
		mapper.add(ppv);
		PropertyValue ppv1 = mapper.getById(ppv.getId());
		assertEquals(ppv1.getValue(), "11111");
		ppv.setValue("22222");
		mapper.update(ppv);
		ppv1 = mapper.getById(ppv.getId());
		assertEquals(ppv1.getValue(), "22222");
		mapper.delete(ppv.getId());
		ppv1 = mapper.getById(ppv.getId());
		assertEquals(ppv1, null);
		System.out.println("test success!");
	}
}

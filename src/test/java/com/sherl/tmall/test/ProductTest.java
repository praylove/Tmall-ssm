package com.sherl.tmall.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sherl.tmall.dao.CategoryMapper;
import com.sherl.tmall.dao.ProductMapper;
import com.sherl.tmall.entity.Product;
import com.sherl.tmall.service.ProductService;
import com.sherl.tmall.web.product.ProductForm;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class ProductTest {

	@Autowired
	private ProductMapper mapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mvc;

	@Autowired
	private Mapper mapping;

	@Before
	public void init() {
		this.mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
		// mapping = new DozerBeanMapper();
	}

	@Test
	public void testDAO() {
		List<Product> ps = mapper.list(3);
		for (Product p : ps) {
			System.out.println(p);
		}
		Product p = new Product();
		p.setName("大衣");
		p.setSubTitle("舒服 保暖");
		p.setOrignalPrice((float) 192.00);
		p.setPromotePrice((float) 172.80);
		p.setStock(1111);
		p.setCreateDate(new Date());
		p.setCategory(categoryMapper.getById(39));
		mapper.add(p);
		List<Product> ps1 = mapper.list(39);
		System.out.println(ps1.get(0).getName());
		assertEquals(ps1.get(0).getName(), p.getName());
		p.setName("棉衣");
		mapper.update(p);
		ps1 = mapper.list(39);
		System.out.println(ps1.get(0).getName());
		assertEquals(ps1.get(0).getName(), "棉衣");
		mapper.delete(p.getId());
		ps1 = mapper.list(39);
		assertEquals(ps1, new ArrayList<>());
		System.out.println("test successful!");
	}

	@Test
	public void testMapping() {
		ProductForm form = new ProductForm();
		form.setName("a");
		form.setOrignalPrice((float) 11.22);
		form.setPromotePrice((float) 10.01);
		form.setStock(111);
		form.setSubTitle("vvvvvv");
		Product product = mapping.map(form, Product.class);
		if (product == null) {
			System.out.println("product = null");
		} else {
			System.out.println(product);
		}
	}

	@Test
	public void testGetRow() {
		List<Product> ps = productService.list(3);
		System.out.println("ps:" + ps);
		List<List<Product>> psByRow = new ArrayList<>();
		List<Product> row = new ArrayList<>();
		int cols = (int) (Math.random() * 2) + 5;
		System.out.println("cols: " + cols);
		for (int i = 0; i < ps.size(); ++i) {
			if ((i + 1) % cols == 0) {
				psByRow.add(row);
				cols = (int) (Math.random() * 2) + 5;
				row = new ArrayList<>();
			}
			row.add(ps.get(i));
		}
		if (!row.isEmpty())
			psByRow.add(row);
		System.out.println(psByRow);
	}

	@Test
	public void testGetByOrder() {
		List<Product> ps = mapper.listByOrder(3, "createDate", "DESC");
		System.out.println(ps);
	}

}

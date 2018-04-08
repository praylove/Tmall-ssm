package com.sherl.tmall.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.CategoryMapper;
import com.sherl.tmall.entity.Category;
import com.sherl.tmall.service.CategoryService;
import com.sherl.tmall.web.category.CategoryForm;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class CategoryTest {

	@Autowired
	private WebApplicationContext webAppContext;

	@Autowired
	private CategoryMapper categoryMapper;

	@Resource
	private CategoryService categoryService;

	// @Autowired
	private Mapper beanMapper;

	private MockMvc mvc;

	@org.junit.Before
	public void init() {
		this.mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
		beanMapper = new DozerBeanMapper();
	}

	@Test
	public void testDAO() {
		PageHelper.startPage(1, 5);
		List<Category> cs = categoryMapper.list();
		PageInfo<Category> info = new PageInfo<>(cs);

		for (Category c : info.getList()) {
			System.out.print(c);
		}

		System.out.println("\n----- done! -----");
	}

	@Test
	public void testCategoryController() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.set("name", "aaaa");
		mvc.perform(post("/admin/categorys").params(map)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testMapping() {
		CategoryForm form = new CategoryForm();
		form.setName("111");
		form.setImage(new MultipartFile() {

			@Override
			public void transferTo(File arg0) throws IOException, IllegalStateException {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		});
		Category c = beanMapper.map(form, Category.class);
		System.out.println(c);
	}
}

package com.sherl.tmall.web.product;

import java.util.Date;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sherl.tmall.entity.Product;
import com.sherl.tmall.service.CategoryService;
import com.sherl.tmall.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping("/admin/{cid}/product")
	public String productIndex() {
		return "/admin/product";
	}

	@RequestMapping(value = "/admin/{cid}/products", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Product> list(@RequestParam(required = false) Integer p, @PathVariable int cid) {
		if (p == null) {
			p = 1;
		}
		return productService.list(cid, p, 5);
	}

	@RequestMapping(value = "/admin/{cid}/products/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product get(@PathVariable int id) {
		return productService.get(id);
	}

	@RequestMapping(value = "/admin/{cid}/products", method = RequestMethod.POST)
	@ResponseBody
	public void add(@Valid ProductForm form, BindingResult result, @PathVariable int cid) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError().getDefaultMessage());
			return;
		}
		Product p = beanMapper.map(form, Product.class);
		p.setCategory(categoryService.get(cid));
		p.setCreateDate(new Date());
		productService.add(p);
	}

	@RequestMapping(value = "/admin/{cid}/products/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@Valid ProductForm form, BindingResult result, @PathVariable int cid, @PathVariable int id) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError().getDefaultMessage());
			return;
		}
		Product p = beanMapper.map(form, Product.class);
		p.setId(id);
		p.setCategory(categoryService.get(cid));
		p.setCreateDate(new Date());
		productService.update(p);
	}

	@RequestMapping(value = "/admin/{cid}/products/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		productService.delete(id);
	}
}

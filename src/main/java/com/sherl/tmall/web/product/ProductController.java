package com.sherl.tmall.web.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/{cid}/products/subtitle", method = RequestMethod.GET)
	@ResponseBody
	public List<List<Product>> listByRow(@PathVariable int cid) {
		List<Product> ps = productService.list(cid);

		List<List<Product>> psByRow = new ArrayList<>();
		List<Product> row = new ArrayList<>();
		int cols = (int) (Math.random() * 2) + 5;

		for (int i = 0; i < ps.size(); ++i) {
			if ((i + 1) % cols == 0) {
				psByRow.add(row);
				cols = (int) (Math.random() * 2);
				row = new ArrayList<>();
			}
			row.add(ps.get(i));
		}

		if (!row.isEmpty())
			psByRow.add(row);

		return psByRow;
	}

	@RequestMapping(value = { "/admin/{cid}/products/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public Product get(@PathVariable int id) {
		return productService.get(id);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String foreProduct(@PathVariable int id, Model model) {
		model.addAttribute("p", productService.get(id));
		return "/fore/product";
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

	@RequestMapping(value = "/{cid}/products", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getByOrder(@PathVariable int cid, @RequestParam String col, @RequestParam String seq) {
		switch (col) {

		case "time":
			return productService.listByNew(cid);
		case "sale":
			return productService.listBySale(cid);
		case "price":
			return productService.listByPrice(cid, seq.equals("desc"));
		default:
			break;
		}

		return productService.listByOrder(cid, col, seq);
	}
}

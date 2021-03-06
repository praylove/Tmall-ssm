package com.sherl.tmall.web.category;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.sherl.tmall.entity.Category;
import com.sherl.tmall.service.CategoryService;
import com.sherl.tmall.service.ProductService;
import com.sherl.tmall.util.FileUploadHelper;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String categoryIndex() {
		return "admin/category";
	}

	@RequestMapping(value = "/admin/categorys", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Category> list(@RequestParam(required = false) Integer p) {
		if (p == null)
			p = 1;
		PageInfo<Category> pageInfo = categoryService.list(p, 5);
		return pageInfo;
	}

	@RequestMapping(value = "/categorys", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> listWithProducts() {
		List<Category> cs = categoryService.list();
		for (Category c : cs) {
			c.setProducts(productService.list(c.getId()));
		}
		return cs;
	}

	@RequestMapping(value = "/admin/categorys/{cid}", method = RequestMethod.GET)
	@ResponseBody
	public Category get(@PathVariable int cid) {
		return categoryService.get(cid);
	}

	@RequestMapping(value = "/admin/categorys", method = RequestMethod.POST)
	@ResponseBody
	public void add1(@Valid CategoryForm form, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError().getDefaultMessage());
			return;
		}
		Category c = beanMapper.map(form, Category.class);
		categoryService.add(c);
		String path = "/resources/image/category";
		if (!FileUploadHelper.uploadImage(request, path, c.getId() + "", form.getImage())) {
			categoryService.delete(c.getId());
		}
	}

	@RequestMapping(value = "/admin/categorys/{cid}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete1(@PathVariable int cid, HttpServletRequest request) {
		categoryService.delete(cid);
		String floderPath = request.getServletContext().getRealPath("/resources/image/category");
		String filePath = floderPath + "/" + cid + ".jpg";
		File file = new File(filePath);
		file.delete();
	}

	@RequestMapping(value = "/admin/categorys/{cid}", method = RequestMethod.PUT)
	@ResponseBody
	public void update1(@Valid CategoryForm form, BindingResult result, @PathVariable int cid,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError().getDefaultMessage());
			return;
		}
		Category c = beanMapper.map(form, Category.class);
		c.setId(cid);
		categoryService.update(c);

		FileUploadHelper.uploadImage(request, "/resources/image/category", c.getId() + "", form.getImage());
	}

	@RequestMapping(value = "/category/{cid}")
	public String categroy(@PathVariable int cid, Model model) {
		Category c = categoryService.get(cid);
		model.addAttribute("c", c);
		return "fore/category";
	}

}

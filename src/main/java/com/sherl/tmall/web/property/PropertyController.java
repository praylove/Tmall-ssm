package com.sherl.tmall.web.property;

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
import com.sherl.tmall.entity.Property;
import com.sherl.tmall.service.CategoryService;
import com.sherl.tmall.service.PropertyService;

@Controller
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Mapper beanMapper;

	// @ModelAttribute
	// public void setForm(Model model) {
	// model.addAttribute("propertyForm", new PropertyForm());
	// }

	@RequestMapping("/admin/{cid}/property")
	public String propertyIndex() {
		return "/admin/property";
	}

	@RequestMapping(value = "/admin/{cid}/propertys", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Property> list(@PathVariable int cid, @RequestParam(required = false) Integer p, Model model) {
		if (p == null)
			p = 1;
		return propertyService.list(cid, p, 5);
	}

	@RequestMapping(value = "/admin/{cid}/propertys", method = RequestMethod.POST)
	@ResponseBody
	public void add(@Valid PropertyForm form, BindingResult result, @PathVariable int cid) {
		if (result.hasErrors()) {
			System.out.println(result);
		}
		Property pp = beanMapper.map(form, Property.class);
		pp.setCategory(categoryService.get(cid));
		propertyService.add(pp);
	}

	@RequestMapping(value = "/admin/{cid}/propertys/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@Valid PropertyForm form, @PathVariable int id, @PathVariable int cid) {
		Property pp = beanMapper.map(form, Property.class);
		pp.setId(id);
		propertyService.update(pp);
	}

	@RequestMapping(value = "/admin/{cid}/propertys/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		propertyService.delete(id);
	}

}

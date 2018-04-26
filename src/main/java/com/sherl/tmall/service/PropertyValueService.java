package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.PropertyValueMapper;
import com.sherl.tmall.entity.Product;
import com.sherl.tmall.entity.Property;
import com.sherl.tmall.entity.PropertyValue;

@Service
public class PropertyValueService {

	@Autowired
	private PropertyValueMapper mapper;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ProductService productService;

	public List<PropertyValue> list(int pid) {
		return mapper.list(pid);
	}

	public PageInfo<PropertyValue> list(int pid, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<PropertyValue> ppvs = mapper.list(pid);
		return new PageInfo<>(ppvs);
	}

	public PropertyValue get(int id) {
		return mapper.getById(id);
	}

	public List<PropertyValue> listEdit(int pid) {
		List<PropertyValue> ppvs = list(pid);
		Product p = productService.get(pid);
		List<Property> pps = propertyService.list(p.getCategory().getId());

		for (int i = 0; i < ppvs.size(); ++i) {
			boolean isContain = false;
			for (int j = 0; j < pps.size(); ++j) {
				if (ppvs.get(i).getProperty().getId() == pps.get(j).getId()) {
					pps.remove(j);
					isContain = true;
					break;
				}
			}
			if (!isContain) {
				delete(ppvs.get(i).getId());
			}
		}

		for (Property pp : pps) {
			PropertyValue ppv = new PropertyValue();
			ppv.setProduct(p);
			ppv.setProperty(pp);
			ppv.setValue("");
			add(ppv);
		}

		return list(pid);
	}

	@Transactional
	public void add(PropertyValue ppv) {
		mapper.add(ppv);
	}

	@Transactional
	public void update(PropertyValue ppv) {
		mapper.update(ppv);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}
}

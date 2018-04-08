package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.PropertyValueMapper;
import com.sherl.tmall.entity.PropertyValue;

@Service
public class PropertyValueService {

	@Autowired
	private PropertyValueMapper mapper;

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

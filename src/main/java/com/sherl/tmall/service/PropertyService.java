package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.PropertyMapper;
import com.sherl.tmall.entity.Property;

@Service
public class PropertyService {

	@Autowired
	private PropertyMapper mapper;

	public List<Property> list(int cid) {
		return mapper.list(cid);
	}

	public PageInfo<Property> list(int cid, int currenPage, int pageSize) {
		PageHelper.startPage(currenPage, pageSize);
		List<Property> pps = mapper.list(cid);
		PageInfo<Property> pageInfo = new PageInfo<>(pps);
		return pageInfo;
	}

	@Transactional
	public void add(Property pp) {
		mapper.add(pp);
	}

	@Transactional
	public void update(Property pp) {
		mapper.update(pp);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}
}

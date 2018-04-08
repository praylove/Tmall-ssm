package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.CategoryMapper;
import com.sherl.tmall.entity.Category;

@Service
public class CategoryService {

	@Autowired
	public CategoryMapper categoryMapper;

	public Category get(int cid) {
		return categoryMapper.getById(cid);
	}

	public List<Category> list() {
		return categoryMapper.list();
	}

	public PageInfo<Category> list(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Category> cs = categoryMapper.list();
		return new PageInfo<Category>(cs);
	}

	@Transactional
	public void add(Category c) {
		categoryMapper.add(c);
	}

	@Transactional
	public void update(Category c) {
		categoryMapper.update(c);
	}

	@Transactional
	public void delete(int cid) {
		categoryMapper.delete(cid);
	}

}

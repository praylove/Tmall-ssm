package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.ReviewMapper;
import com.sherl.tmall.entity.Review;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper mapper;

	public List<Review> list(int pid) {
		return mapper.list(pid);
	}

	public PageInfo<Review> list(int pid, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Review> rs = mapper.list(pid);
		return new PageInfo<>(rs);
	}

	public void add(Review r) {
		mapper.add(r);
	}

	public void delete(int id) {
		mapper.delete(id);
	}
}

package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Review;

@Repository
public interface ReviewMapper {

	public List<Review> list(int pid);

	public Review getById(int id);

	public void add(Review r);

	public void delete(int id);

}

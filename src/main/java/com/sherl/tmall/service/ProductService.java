package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.ProductMapper;
import com.sherl.tmall.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductMapper mapper;

	@Autowired
	private ProductImageService imageService;

	public List<Product> list(int cid) {
		List<Product> ps = mapper.list(cid);
		for (Product p : ps) {
			p.setFirstProductImage(imageService.getFirstProductImage(p.getId()));
		}
		return ps;
	}

	public PageInfo<Product> list(int cid, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Product> ps = mapper.list(cid);
		for (Product p : ps) {
			p.setFirstProductImage(imageService.getFirstProductImage(p.getId()));
		}
		return new PageInfo<>(ps);
	}

	public Product get(int id) {
		Product p = mapper.getById(id);
		p.setFirstProductImage(imageService.getFirstProductImage(id));
		p.setProductDetailsImages(imageService.getProductDetailsImage(id));
		p.setProductSingleImages(imageService.getProductSingleImage(id));
		return p;
	}

	@Transactional
	public void add(Product p) {
		mapper.add(p);
	}

	@Transactional
	public void update(Product p) {
		mapper.update(p);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}
}

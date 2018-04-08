package com.sherl.tmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherl.tmall.dao.ProductImageMapper;
import com.sherl.tmall.entity.ProductImage;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageMapper mapper;

	private final static String SINGLE = "single";
	private final static String DETAILS = "details";

	public List<ProductImage> list(int pid) {
		return mapper.list(pid);
	}

	public List<ProductImage> listByType(int pid, String type) {
		return mapper.listByType(pid, type);
	}

	public ProductImage get(int id) {
		return mapper.getById(id);
	}

	public ProductImage getFirstProductImage(int pid) {
		List<ProductImage> singleImages = getProductSingleImage(pid);
		if (singleImages.isEmpty())
			return null;
		return singleImages.get(0);
	}

	public List<ProductImage> getProductDetailsImage(int pid) {
		return listByType(pid, DETAILS);
	}

	public List<ProductImage> getProductSingleImage(int pid) {
		return listByType(pid, SINGLE);
	}

	public void addDetailsImage(ProductImage pi) {
		pi.setType(DETAILS);
		this.add(pi);
	}

	public void addSingleImage(ProductImage pi) {
		pi.setType(SINGLE);
		this.add(pi);
	}

	@Transactional
	public void add(ProductImage pi) {
		mapper.add(pi);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}

}

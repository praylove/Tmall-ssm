package com.sherl.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.ProductImage;

@Repository
public interface ProductImageMapper {

	public List<ProductImage> list(int pid);

	public ProductImage getById(int id);

	public List<ProductImage> listByType(@Param("pid") int pid, @Param("type") String type);

	public void add(ProductImage pi);

	// public void update(ProductImage bean);

	public void delete(int id);
}

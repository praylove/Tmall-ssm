package com.sherl.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.Product;

@Repository
public interface ProductMapper {

	/**
	 * 时间排序方式中对应的字段
	 */
	static final String TIME = "createDate";

	/**
	 * 销量排序方式中对应的字段
	 */
	static final String SALE = "sale";

	/**
	 * 价格排序方式中对应的字段
	 */
	static final String PRICE = "promotionprice";

	static final String INCREASE = "ASC";

	static final String DECREASE = "DESC";

	public List<Product> list(int cid);

	public List<Product> listByOrder(@Param("cid") int cid, @Param("col") String col, @Param("seq") String seq);

	public Product getById(int id);

	public void add(Product p);

	public void update(Product p);

	public void updateSale(Product p);

	public void delete(int id);
}

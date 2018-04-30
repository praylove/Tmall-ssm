package com.sherl.tmall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherl.tmall.dao.OrderItemMapper;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.Product;

@Service
public class ShoppingCarService {

	@Autowired
	private OrderItemMapper mapper;

	@Autowired
	private UserService userSerivce;

	@Autowired
	private OrderItemService itemSerive;

	@Autowired
	private ProductImageService imageService;

	@Autowired
	private ProductService productService;

	public List<OrderItem> list(int uid) {
		List<OrderItem> ois = mapper.carList(uid);
		for (OrderItem oi : ois) {
			Product p = oi.getProduct();
			p.setFirstProductImage(imageService.getFirstProductImage(p.getId()));
		}
		return ois;
	}

	public List<OrderItem> listByArray(Integer[] ids) {
		List<OrderItem> ois = new ArrayList<>();
		for (Integer id : ids) {
			if (id == null)
				continue;
			OrderItem oi = get(id);
			if (oi != null)
				ois.add(oi);
		}
		return ois;
	}

	public int count(int uid) {
		return mapper.carCount(uid);
	}

	public OrderItem get(int uid, int pid) {
		return mapper.getByProductAndUser(uid, pid);
	}

	public OrderItem get(int id) {
		OrderItem oi = mapper.getCarById(id);
		Product p = oi.getProduct();
		p.setFirstProductImage(imageService.getFirstProductImage(p.getId()));
		return oi;
	}

	public void carAddOperation(int uid, int pid, int number) {
		OrderItem oi = get(uid, pid);
		if (oi == null) {
			oi = new OrderItem();
			oi.setUser(userSerivce.get(uid));
			oi.setProduct(productService.get(pid));
			oi.setNumber(number);
			oi.setPrices(oi.getNumber() * oi.getProduct().getPromotePrice());
			addItem(oi);
		} else {
			oi.setNumber(number);
			oi.setPrices(oi.getNumber() * oi.getProduct().getPromotePrice());
			updateItem(oi);
		}
	}

	public void updateItem(int id, int number) {
		OrderItem oi = get(id);
		if (oi == null)
			return;
		oi.setNumber(number);
		updateItem(oi);
	}

	@Transactional
	public void addItem(OrderItem oi) {
		Order o = new Order();
		o.setId(0);
		oi.setOrder(o);
		mapper.add(oi);
	}

	@Transactional
	public void updateItem(OrderItem oi) {
		Order o = new Order();
		o.setId(0);
		oi.setOrder(o);
		mapper.update(oi);
	}

	@Transactional
	public void increaseNumber(int id) {
		OrderItem oi = mapper.getById(id);
		if (oi == null || oi.getOrder().getId() != 0)
			return;
		oi.setNumber(oi.getNumber() + 1);
		updateItem(oi);
	}

	public void delete(int id) {
		itemSerive.delete(id);
	}

	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			if (id != null)
				delete(id);
		}
	}
}

package com.sherl.tmall.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sherl.tmall.dao.OrderMapper;
import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.Status;

@Service
public class OrderService {

	@Autowired
	private OrderMapper mapper;

	// private final static String UNPAY = "待支付";
	// private final static String UNDELIVER = "待发货";
	// private final static String UNCONFIRM = "待收货";
	// private final static String UNREVIEW = "待评论";
	// private final static String SUCCESS = "已完成";
	// private final static String CANCELLED = "已取消";

	public List<Order> list() {
		return mapper.list();
	}

	public PageInfo<Order> list(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Order> os = mapper.list();
		return new PageInfo<>(os);
	}

	public Order get(int id) {
		return mapper.getById(id);
	}

	/**
	 * @param o
	 *            order需要 address,post,receiver,mobile,userMessage,<b>user</b>
	 */
	@Transactional
	public void create(Order o) {
		o.setOrderCode(o.codeGenerator());
		o.setStatus(Status.UNPAY);
		o.setCreateDate(new Date());
		mapper.add(o);
	}

	@Transactional
	public void cancel(int id) {
		Order o = this.get(id);
		if (!o.getStatus().equals(Status.UNPAY))
			return;
		o.setStatus(Status.CANCELLED);
		mapper.updateStatus(o);
	}

	@Transactional
	public void pay(int id) {
		Order o = this.get(id);
		if (!o.getStatus().equals(Status.UNPAY))
			return;
		o.setStatus(Status.UNDELIVER);
		o.setPayDate(new Date());
		mapper.updateStatus(o);
	}

	@Transactional
	public void delivery(int id) {
		Order o = this.get(id);
		if (!o.getStatus().equals(Status.UNDELIVER))
			return;
		o.setStatus(Status.UNCONFIRM);
		o.setDeliveryDate(new Date());
		mapper.updateStatus(o);
	}

	@Transactional
	public void confirm(int id) {
		Order o = this.get(id);
		if (!o.getStatus().equals(Status.UNCONFIRM))
			return;
		o.setStatus(Status.UNREVIEW);
		o.setConfirmDate(new Date());
		mapper.updateStatus(o);
	}

	@Transactional
	public void review(int id) {
		Order o = this.get(id);
		if (!o.getStatus().equals(Status.UNREVIEW))
			return;
		o.setStatus(Status.SUCCESS);
		o.setReviewDate(new Date());
		mapper.updateStatus(o);
	}

	@Transactional
	public void update(Order o) {
		mapper.update(o);
	}

	@Transactional
	public void updateStatus(Order o) {
		mapper.updateStatus(o);
	}

	@Transactional
	public void delete(int id) {
		mapper.delete(id);
	}

}

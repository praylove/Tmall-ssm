package com.sherl.tmall.web.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sherl.tmall.entity.Order;
import com.sherl.tmall.entity.OrderItem;
import com.sherl.tmall.entity.Review;
import com.sherl.tmall.entity.User;
import com.sherl.tmall.entity.UserDetailsImpl;
import com.sherl.tmall.service.OrderService;
import com.sherl.tmall.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/s-order/{id}/review-success")
	public String review(@PathVariable int id, @RequestParam String content) {
		Review r = new Review();
		r.setContent(content);
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(o instanceof UserDetailsImpl)) {
			return "redirect:/";
		}
		User user = ((UserDetailsImpl) o).getUser();
		r.setUser(user);
		Order order = orderService.get(id);
		for (OrderItem oi : order.getOrderItems()) {
			r.setProduct(oi.getProduct());
			reviewService.add(r);
		}
		orderService.review(id);

		return "fore/review";
	}
}

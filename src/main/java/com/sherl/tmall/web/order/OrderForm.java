package com.sherl.tmall.web.order;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrderForm {

	@NotNull
	private String address;

	@NotNull
	@Pattern(regexp = "^\\d{6}$")
	private String post;

	@NotNull
	private String receiver;

	@NotNull
	@Pattern(regexp = "^1[3|5|7]\\d{9}$")
	private String mobile;

	@Size(max = 200)
	private String userMessage;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}

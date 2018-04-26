package com.sherl.tmall.web.shoppingcar;

import javax.validation.constraints.Min;

public class ShoppingCarForm {

	@Min(1)
	private int pid;

	@Min(1)
	private int number;

	public ShoppingCarForm() {

	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}

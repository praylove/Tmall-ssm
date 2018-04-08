package com.sherl.tmall.web.product;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductForm {

	@NotNull
	@Size(min = 2, max = 10)
	private String name;

	@Size(max = 20)
	private String subTitle;

	@NotNull
	@Digits(integer = 20, fraction = 2)
	private float orignalPrice;

	@NotNull
	@Digits(integer = 20, fraction = 2)
	private float promotePrice;

	@NotNull
	@Min(0)
	private int stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public float getOrignalPrice() {
		return orignalPrice;
	}

	public void setOrignalPrice(float orignalPrice) {
		this.orignalPrice = orignalPrice;
	}

	public float getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductForm [name=" + name + ", subTitle=" + subTitle + ", orignalPrice=" + orignalPrice
				+ ", promotePrice=" + promotePrice + ", stock=" + stock + "]";
	}

}

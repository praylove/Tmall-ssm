package com.sherl.tmall.web.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserForm {

	@NotNull
	@Pattern(regexp = "^\\w{2,10}$")
	private String name;

	@NotNull
	@Pattern(regexp = "^\\w{6,16}$")
	private String password1;

	@NotNull
	@Pattern(regexp = "^\\w{6,16}$")
	private String password2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}

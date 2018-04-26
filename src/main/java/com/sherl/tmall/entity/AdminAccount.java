package com.sherl.tmall.entity;

import java.io.Serializable;

@Deprecated
public class AdminAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String adminName;
	private String password;
	private String role;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminAccount [adminName=" + adminName + ", password=" + password + ", role=" + role + "]";
	}
}

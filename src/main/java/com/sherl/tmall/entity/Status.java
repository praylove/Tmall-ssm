package com.sherl.tmall.entity;

public enum Status {
	UNPAY("待支付", 1), UNDELIVER("待发货", 2), UNCONFIRM("待收货", 3), UNREVIEW("待评论", 4), SUCCESS("已完成", 5), CANCELLED("已取消",
			6);

	private String name;
	private int id;

	private Status() {

	}

	private Status(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public static Status get(int id) {
		for (Status s : Status.values()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

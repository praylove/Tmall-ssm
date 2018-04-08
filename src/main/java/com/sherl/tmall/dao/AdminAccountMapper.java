package com.sherl.tmall.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.AdminAccount;

@Repository
public interface AdminAccountMapper {

	public List<AdminAccount> list();

	public AdminAccount getByID(int id);

	public void add(AdminAccount bean);

	public void update(AdminAccount bean);

	public void delete(int id);

}

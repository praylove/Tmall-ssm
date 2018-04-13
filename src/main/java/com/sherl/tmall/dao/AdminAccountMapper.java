package com.sherl.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sherl.tmall.entity.AdminAccount;

@Repository
public interface AdminAccountMapper {

	public AdminAccount getByName(String adminname);

	public AdminAccount getByNameAndPassword(@Param("adminname") String adminname, @Param("password") String password);

	public List<AdminAccount> list();
}

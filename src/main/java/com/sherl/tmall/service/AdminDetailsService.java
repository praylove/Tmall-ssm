package com.sherl.tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sherl.tmall.entity.AdminAccount;
import com.sherl.tmall.entity.AdminDetails;

@Service("adminDetailsService")
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminAccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminAccount account = accountService.get(username);
		if (account == null) {
			throw new UsernameNotFoundException(username + "is not found");
		}
		return new AdminDetails(account);
	}

}

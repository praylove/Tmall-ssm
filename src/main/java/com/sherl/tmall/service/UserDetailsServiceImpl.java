package com.sherl.tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sherl.tmall.entity.User;
import com.sherl.tmall.entity.UserDetailsImpl;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.get(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "is not found!");
		}
		return new UserDetailsImpl(user);
	}

}

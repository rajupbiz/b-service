package com.blob.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blob.dao.UserDao;
import com.blob.model.User;

@Component
@Transactional
public class UserDetailService implements UserDetailsService {

	@Resource
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userdetails = null;
		User user = userDao.findByUsername(username);
		if(user != null){
			// TODO : add flags for user in table and set here for the username like isLocked etc
			userdetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, getAuthorities(user.getId()));
		}
		return userdetails;
	}

	private Collection<GrantedAuthority> getAuthorities(Long userId){
		//TODO : Create table for UserRole mapping and get roles for the userId
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(2);
		GrantedAuthority adminRole = new SimpleGrantedAuthority("ADMIN");
		//GrantedAuthority userRole = new SimpleGrantedAuthority("USER");
		authorities.add(adminRole);
		//authorities.add(userRole);
		authorities.add(adminRole);
		return authorities;
	}
}

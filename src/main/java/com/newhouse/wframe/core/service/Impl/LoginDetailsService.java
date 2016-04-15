package com.newhouse.wframe.core.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.newhouse.wframe.common.base.IServiceBase;
import com.newhouse.wframe.core.entity.Role;
import com.newhouse.wframe.core.entity.User;
import com.newhouse.wframe.core.repository.RoleDao;
import com.newhouse.wframe.core.repository.UserDao;

public class LoginDetailsService implements IServiceBase, UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(username);
		List<Role> listRole = roleDao.findByUsers_Id(user.getId());
		
		return buildUserDetails(user, listRole);
		
	}
	
	
	private org.springframework.security.core.userdetails.User buildUserDetails(User user, List<Role> listRole){
		
		List<GrantedAuthority> listAuths = new ArrayList<GrantedAuthority>();
		
		for(Role  r : listRole){
			listAuths.add(new SimpleGrantedAuthority(r.getValue()));
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, true, listAuths
				);
	}

}

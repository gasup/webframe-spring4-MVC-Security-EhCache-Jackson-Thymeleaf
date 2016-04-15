package com.newhouse.wframe.core.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newhouse.wframe.common.base.IServiceBase;
import com.newhouse.wframe.core.entity.Department;
import com.newhouse.wframe.core.entity.User;
import com.newhouse.wframe.core.repository.UserDao;



@Service("userManageService")
@CacheConfig(cacheNames="user")
public class UserManageService implements IServiceBase{

	@Autowired
	UserDao userDao;
	
	@Caching(
		put = { 
			@CachePut(key = "#user.id"), 
			@CachePut(key = "#user.username")
		},
		evict={
			@CacheEvict(key="'listUser'")
		}
		
	)
	@Target({ ElementType.METHOD, ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	public @interface UserSaveCache {
	}
	
	public UserManageService() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Transactional
	@UserSaveCache
	public User addUser(User user){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		user.setDepartment(new Department(Long.valueOf(1)));
		return userDao.save(user);
	}
	
	//@Transactional(readOnly=true)
	@Cacheable(key="#root.methodName")
	public List<User> listUser(){
		List<User> iUser = userDao.findUsers();
		for(User u : (List<User>)iUser){
			u.getRoles().isEmpty();
		}
		return (List<User>)iUser;
	}
	
	@Cacheable(key="#username")
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	@Cacheable(key="#id")	
	public User findById(Long id){
		return userDao.findOne(id);
	}
	
	@CacheEvict(key="#id")
	public void delete(Long id){
		userDao.delete(id);
	}
	
}

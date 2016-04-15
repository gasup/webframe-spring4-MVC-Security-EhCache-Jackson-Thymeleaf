package com.newhouse.wframe.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newhouse.wframe.common.base.IResponseBase;
import com.newhouse.wframe.core.entity.User;

public interface UserDao extends IResponseBase< User, Long>{
	
	@Query("select u from com.newhouse.wframe.core.entity.User u left join fetch u.department d left join fetch u.roles r where u.username=:un")
	public User findByUsername(@Param("un")  String username);
	
	@Query("select u from com.newhouse.wframe.core.entity.User u left join fetch u.department d left join fetch u.roles r")
	public List<User> findUsers();
}

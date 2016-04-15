package com.newhouse.wframe.core.repository;

import java.util.List;

import com.newhouse.wframe.common.base.IResponseBase;
import com.newhouse.wframe.core.entity.Role;

public interface RoleDao extends IResponseBase<Role, Long> {
	
	List<Role> findByUsers_Id(Long id);
}

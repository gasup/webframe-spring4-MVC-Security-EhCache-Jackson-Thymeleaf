package com.newhouse.wframe.core.controllerREST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.newhouse.wframe.common.pojo.JsonResult;
import com.newhouse.wframe.core.entity.User.DetailUserView;
import com.newhouse.wframe.core.entity.User.ListUserView;
import com.newhouse.wframe.core.service.UserManageService;


@Controller
@RequestMapping(value="/datas/user")
public class UserDataCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserDataCtrl.class);
	
	@Autowired
	UserManageService service;
	
	@RequestMapping("list")
	@ResponseBody
	@JsonView(ListUserView.class)
	public JsonResult list(){
		return new JsonResult(service.listUser());
	}
	
	@RequestMapping("detail/{id}")
	@ResponseBody
	@JsonView(DetailUserView.class)
	public JsonResult detail(@PathVariable("id") String id){
		return new JsonResult(service.findById(Long.valueOf(id)));
	}
	
}

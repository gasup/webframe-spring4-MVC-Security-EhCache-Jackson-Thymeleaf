package com.newhouse.wframe.core.controllerView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhouse.wframe.core.entity.User;
import com.newhouse.wframe.core.service.UserManageService;

@Controller
@RequestMapping(value="/views/user")
public class UserViewCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserViewCtrl.class);
	
	@Autowired
	UserManageService service;
	
	
	
	public UserViewCtrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value="addView", method = RequestMethod.GET)
	public String addView(Model model){
		
		return "adduser";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user, Model model){
		
		User ret = service.addUser(user);
		model.addAttribute("userId", ret.getId());
		
		return "adduser";
	}
	
	@RequestMapping(value="list")
	public String listUser(Model model){
		model.addAttribute("users", service.listUser());
		return "listUser";
	}
	
	@RequestMapping("detail/{username}")
	public String detail(@PathVariable("username") String username, Model model){
		User user = service.findByUsername(username);
		model.addAttribute("user", user);
		return "detailUser";
	}
	
}

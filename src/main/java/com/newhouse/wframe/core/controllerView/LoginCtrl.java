package com.newhouse.wframe.core.controllerView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginCtrl {
	private static final Logger logger = LoggerFactory.getLogger(LoginCtrl.class);
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}

}

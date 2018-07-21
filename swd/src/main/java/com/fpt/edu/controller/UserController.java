package com.fpt.edu.controller;

import com.fpt.edu.entities.User;
import com.fpt.edu.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	UserServices userServices;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String updateOwnerShipDetails(@RequestParam String username, @RequestParam String password) throws Exception {
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		boolean result=userServices.create(user);
		if(result){
			return "login";

		}
		return "/WEB-INF/register.jsp";



	}
	
	
	
	
	
	
	
}

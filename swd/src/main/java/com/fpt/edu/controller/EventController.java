package com.fpt.edu.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EventController {

	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String updateOwnerShipDetails()  {

			return "login";	
	}
	
	
	
	
	
	
	
}

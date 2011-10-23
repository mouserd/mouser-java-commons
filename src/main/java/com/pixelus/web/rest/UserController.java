package com.pixelus.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixelus.user.User;
import com.pixelus.user.UserService;

@Controller
@RequestMapping(value="/api/user")
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(UserController.class);

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	
	
	@RequestMapping(value = "/{id}.json", method = RequestMethod.GET)
	public @ResponseBody User getUserDetails(@PathVariable Long id) {
		
		LOG.info("Getting user details for user id: " + id);
		return userService.findById(id);
	}
}

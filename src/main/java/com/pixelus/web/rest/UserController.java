package com.pixelus.web.rest;

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
@RequestMapping(value = "/api/user")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/{id}.json", method = RequestMethod.GET)
    @ResponseBody
    public User getUserDetails(@PathVariable final Long id) {

        LOG.info("Getting user details for user id: " + id);
        return userService.findById(id);
    }
}

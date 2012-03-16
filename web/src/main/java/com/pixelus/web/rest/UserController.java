/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.web.rest;

import com.pixelus.entity.User;
import com.pixelus.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/{id}.json", method = GET)
    @ResponseBody
    public final User getUserDetails(@PathVariable final Long id) {

        LOG.info("Getting user details for user id: " + id);
        return userService.findById(id);
    }
}

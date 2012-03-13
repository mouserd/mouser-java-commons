/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */
package com.pixelus.user.spi;

import com.pixelus.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixelus.spi.AbstractServiceImpl;
import com.pixelus.user.UserDao;
import com.pixelus.user.UserService;

@Service
public class UserServiceImpl
        extends AbstractServiceImpl<User, Long>
        implements UserService {

    @Autowired
    public UserServiceImpl(final UserDao dao) {
        super(dao);
    }
}

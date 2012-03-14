/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.service.spi;

import com.pixelus.entity.User;
import com.pixelus.repository.UserRepository;
import com.pixelus.service.AbstractServiceImpl;
import com.pixelus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
      extends AbstractServiceImpl<User, Long>
      implements UserService {

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {

        super(userRepository);
    }
}

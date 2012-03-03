package com.pixelus.user.spi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixelus.spi.AbstractServiceImpl;
import com.pixelus.user.User;
import com.pixelus.user.UserDao;
import com.pixelus.user.UserService;

@Service
public class UserServiceImpl
        extends AbstractServiceImpl<User, Long>
        implements UserService {

    @Autowired
    public UserServiceImpl(UserDao dao) {
        super(dao);
    }
}

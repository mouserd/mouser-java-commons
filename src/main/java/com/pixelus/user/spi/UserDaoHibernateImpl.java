package com.pixelus.user.spi;

import com.pixelus.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pixelus.spi.AbstractHibernateDaoImpl;
import com.pixelus.user.UserDao;

@Repository
public class UserDaoHibernateImpl
        extends AbstractHibernateDaoImpl<User, Long>
        implements UserDao {

    @Autowired
    public UserDaoHibernateImpl(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

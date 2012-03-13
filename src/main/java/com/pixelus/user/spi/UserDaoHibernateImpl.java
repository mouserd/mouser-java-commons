/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.user.spi;

import com.pixelus.db.AbstractHibernateDaoImpl;
import com.pixelus.user.User;
import com.pixelus.user.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoHibernateImpl
        extends AbstractHibernateDaoImpl<User, Long>
        implements UserDao {

    @Autowired
    public UserDaoHibernateImpl(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository.spi;

import com.pixelus.entity.User;
import com.pixelus.repository.AbstractHibernateRepositoryImpl;
import com.pixelus.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryHibernateImpl
      extends AbstractHibernateRepositoryImpl<User, Long>
      implements UserRepository {

    @Autowired
    public UserRepositoryHibernateImpl(final SessionFactory sessionFactory) {

        super(sessionFactory);
    }
}

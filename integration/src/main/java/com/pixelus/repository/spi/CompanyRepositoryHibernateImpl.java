/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository.spi;

import com.pixelus.entity.Company;
import com.pixelus.repository.AbstractHibernateRepositoryImpl;
import com.pixelus.repository.CompanyRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepositoryHibernateImpl
      extends AbstractHibernateRepositoryImpl<Company, Long>
      implements CompanyRepository {

    @Autowired
    public CompanyRepositoryHibernateImpl(final SessionFactory sessionFactory) {

        super(sessionFactory);
    }
}

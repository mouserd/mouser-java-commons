package com.pixelus.company.spi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pixelus.company.Company;
import com.pixelus.company.CompanyDao;
import com.pixelus.spi.AbstractHibernateDaoImpl;

@Repository
public class CompanyDaoHibernateImpl
        extends AbstractHibernateDaoImpl<Company, Long>
        implements CompanyDao {

    @Autowired
    public CompanyDaoHibernateImpl(final SessionFactory sessionFactory) {

        super(sessionFactory);
    }
}

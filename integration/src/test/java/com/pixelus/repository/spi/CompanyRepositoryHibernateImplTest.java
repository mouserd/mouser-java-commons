/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */
package com.pixelus.repository.spi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context-persistence-test.xml" })
public class CompanyRepositoryHibernateImplTest
      extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CompanyRepositoryHibernateImpl companyDao;

    @Before
    public void setUp() {

        this.executeSqlScript("/com/pixelus/company/company.sql", false);
    }

    @Test
    public void testShouldFindAllCompanies() {

        assertEquals(companyDao.findAll().size(), 1);
    }
}

/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */
package com.pixelus.repository.spi;

import com.pixelus.entity.Company;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context-persistence-test.xml"})
public class CompanyRepositoryHibernateImplTest
    extends AbstractTransactionalJUnit4SpringContextTests {

  public static final String COMPANY_NAME = "Pixelus Consulting";
  public static final long COMPANY_ID = 1L;

  @Autowired
  private CompanyRepositoryHibernateImpl companyRepository;

  @Autowired
  private SessionFactory sessionFactory;

  @Before
  public void setUp() {

    this.executeSqlScript("/com/pixelus/company/company.sql", false);
  }

  @Test
  public void findByIdShouldFindCompany() {

    assertNotNull(companyRepository.findById(COMPANY_ID));
  }

  @Test
  public void findAllShouldFindAllCompanies() {

    assertThat(companyRepository.findAll().size(), is(2));
  }

  @Test
  public void updateShouldUpdateExistingCompany() {

    Company company = companyRepository.findById(COMPANY_ID);
    company.setName(company.getName() + " - UPDATED");

    companyRepository.update(company);

    // Explicitly flush the session to ensure it's not being cached!
    sessionFactory.getCurrentSession().flush();

    Company updatedCompany = companyRepository.findById(1L);
    assertThat(company.getName(), is(updatedCompany.getName()));
  }

  @Test
  public void saveShouldCreateNewCompany() {

    int numUsers = companyRepository.findAll().size();

    final Company user = createCompany();
    companyRepository.save(user);

    // Explicitly flush the session to ensure it's not being cached!
    sessionFactory.getCurrentSession().flush();

    assertEquals(companyRepository.findAll().size(), numUsers + 1);
  }

  @Test
  public void deleteShouldDeleteExistingCompany() {

    Company company = companyRepository.findById(COMPANY_ID);
    assertNotNull(company);

    companyRepository.delete(company);

    // Explicitly flush the session to ensure it's not being cached!
    sessionFactory.getCurrentSession().flush();

    assertNull(companyRepository.findById(COMPANY_ID));
  }

  private Company createCompany() {

    Company company = new Company();
    company.setName(COMPANY_NAME);

    return company;
  }
}

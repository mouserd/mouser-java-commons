package com.pixelus.company.spi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pixelus.company.CompanyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-datasource-test.xml", "/context-persistence.xml"})
public class CompanyDaoHibernateImplTest
	extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private CompanyDao companyDao;
	
	@Before
	public void setup() {
		
		this.executeSqlScript("/com/pixelus/company/company.sql", false);
	}
	
	@Test
	public void testShouldFindAllCompanies() {
		
		assertEquals(companyDao.findAll().size(), 1); 
	}
}

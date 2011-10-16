package com.pixelus.user.spi;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pixelus.company.Company;
import com.pixelus.user.User;
import com.pixelus.user.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/persistence.xml"})
public class UserDaoHibernateImplTest
	extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String TEST_SURNAME = "Bennet";
	private static final String TEST_FIRSTNAME = "David";
	private static final Long TEST_COMPANY_ID = 1L;
	
	@Autowired
	private UserDao userDao;
	
	@Before
	public void setup() {
		
		executeSqlScript("/com/pixelus/company/company.sql", false);
	}
	
	@Test
	public void testFindAllUsers() {
		
		executeSqlScript("/com/pixelus/user/user.sql", false);
		
		List<User> users = userDao.findAll();
		
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testSaveUserShouldInsertRow() {
		
		int numUsers = userDao.findAll().size();
		
		
		final User user = createUser();
		userDao.save(user);
		
		assertEquals(userDao.findAll().size(), numUsers+1);
	}

	private User createUser() {
		final User user = new User();
		user.setFirstName(TEST_FIRSTNAME);
		user.setSurname(TEST_SURNAME);
		user.setCompany(new Company(TEST_COMPANY_ID));
		
		return user;
	}
	
	@Test
	public void testSaveUserShouldSetId() {
		
		final User user = createUser();
		
		userDao.save(user);
		assertNotNull("Users id should not have been set", user.getId());
	}
}

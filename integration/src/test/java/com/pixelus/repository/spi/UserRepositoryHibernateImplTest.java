/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository.spi;

import com.pixelus.entity.Company;
import com.pixelus.entity.User;
import com.pixelus.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context-datasource-test.xml", "/context-persistence.xml"})
public class UserRepositoryHibernateImplTest
      extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Long TEST_USER_ID = 1L;
    private static final String TEST_SURNAME = "Bon";
    private static final String TEST_FIRSTNAME = "David";
    private static final Long TEST_COMPANY_ID = 1L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setUp() {

        executeSqlScript("/com/pixelus/company/company.sql", false);
        executeSqlScript("/com/pixelus/user/user.sql", false);
    }

    @Test
    public void findUserByIdShouldFindUser() {

        User user = userRepository.findById(TEST_USER_ID);

        assertNotNull(user);
    }

    @Test
    public void findAllUsersShouldFindUsers() {

        List<User> users = userRepository.findAll();

        assertTrue(users.size() > 0);
    }

    @Test
    public void saveUserShouldCreateNewUser() {

        int numUsers = userRepository.findAll().size();

        final User user = createUser();
        userRepository.save(user);

        // Explicitly flush the session to ensure it's not being cached!
        sessionFactory.getCurrentSession().flush();

        assertEquals(userRepository.findAll().size(), numUsers + 1);
    }

    @Test
    public void updateUserShouldUpdateExistingUser() {

        User user = userRepository.findById(TEST_USER_ID);
        user.setFirstName(user.getFirstName() + " - UPDATED");

        userRepository.update(user);

        // Explicitly flush the session to ensure it's not being cached!
        sessionFactory.getCurrentSession().flush();

        User updatedUser = userRepository.findById(TEST_USER_ID);
        assertThat(updatedUser.getFirstName(), is(user.getFirstName()));
    }

    @Test
    public void deleteShouldDeleteExistingUser() {

        User user = userRepository.findById(TEST_USER_ID);
        assertNotNull(user);
        userRepository.delete(user);

        // Explicitly flush the session to ensure it's not being cached!
        sessionFactory.getCurrentSession().flush();

        user = userRepository.findById(TEST_USER_ID);
        assertNull(user);
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

        userRepository.save(user);
        assertNotNull("Users id should not have been set", user.getId());
    }
}

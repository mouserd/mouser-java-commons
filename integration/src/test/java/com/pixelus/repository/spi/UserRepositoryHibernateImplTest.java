/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.pixelus.entity.Company;
import com.pixelus.entity.User;
import com.pixelus.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context-datasource-test.xml", "/context-persistence.xml"})
public class UserRepositoryHibernateImplTest
      extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Long TEST_USER_ID = 1L;
    private static final String TEST_SURNAME = "Bennet";
    private static final String TEST_FIRSTNAME = "David";
    private static final Long TEST_COMPANY_ID = 1L;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {

        executeSqlScript("/com/pixelus/company/company.sql", false);
    }

    @Test
    public void testFindUserById() {

        executeSqlScript("/com/pixelus/user/user.sql", false);

        User user = userRepository.findById(TEST_USER_ID);

        assertNotNull(user);
    }

    @Test
    public void testFindAllUsers() {

        executeSqlScript("/com/pixelus/user/user.sql", false);

        List<User> users = userRepository.findAll();

        assertTrue(users.size() > 0);
    }

    @Test
    public void testSaveUserShouldInsertRow() {

        int numUsers = userRepository.findAll().size();

        final User user = createUser();
        userRepository.save(user);

        assertEquals(userRepository.findAll().size(), numUsers + 1);
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

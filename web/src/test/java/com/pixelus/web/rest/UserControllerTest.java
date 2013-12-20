/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.web.rest;

import com.pixelus.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/*
 * @author David Mouser
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private static final long ID = 1L;

  private UserController userController;

  @Mock
  private UserService userService;

  @Before
  public void setUp() {

    userController = new UserController(userService);
  }

  @Test
  public void getUserDetailsShouldCallService() {

    userController.getUserDetails(ID);

    verify(userService).findById(1L);
  }
}
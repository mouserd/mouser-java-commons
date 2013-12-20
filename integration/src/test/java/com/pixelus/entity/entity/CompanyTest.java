/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.entity.entity;

import com.pixelus.entity.Company;
import com.pixelus.test.utils.GetterSetterAsserter;
import org.junit.Test;

/*
* @author David Mouser
*/
public class CompanyTest {

  @Test
  public void testGettersAndSetters() {

    new GetterSetterAsserter().assertAllGettersAndSetters(new Company());
  }
}
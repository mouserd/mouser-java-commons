/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;

import org.hibernate.SessionFactory;

/*
* @author David Mouser
*/
public class StubInvalidHibernateRepositoryImpl
    extends AbstractHibernateRepositoryImpl {

  public StubInvalidHibernateRepositoryImpl(SessionFactory sessionFactory) {

    super(sessionFactory);
  }
}

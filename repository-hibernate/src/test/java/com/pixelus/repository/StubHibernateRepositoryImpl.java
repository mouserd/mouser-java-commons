/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;

import com.pixelus.entity.StubEntity;
import org.hibernate.SessionFactory;

/*
* @author David Mouser
*/
public class StubHibernateRepositoryImpl
    extends AbstractHibernateRepositoryImpl<StubEntity, Long> {

  public StubHibernateRepositoryImpl(SessionFactory sessionFactory) {

    super(sessionFactory);
  }
}

/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;

import com.pixelus.entity.ModelEntity;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractHibernateRepositoryImpl<T extends ModelEntity<?>, K>
    implements Repository<T, K> {

  private static final Logger LOG =
      Logger.getLogger(AbstractHibernateRepositoryImpl.class);

  private final SessionFactory sessionFactory;
  private final Class<T> entityClass;

  @SuppressWarnings("unchecked")
  @Autowired
  public AbstractHibernateRepositoryImpl(final SessionFactory sessionFactory) {

    this.sessionFactory = sessionFactory;
    entityClass = getEntityClass();
  }


  @Override
  @Transactional
  public final void save(final T entity) {

    LOG.debug("Creating " + entityClass.getName() + " (id: " + entity.getId() + ")");
    Session session = getCurrentSession();
    session.save(entity);
  }

  @Override
  @Transactional
  public final void update(T entity) {

    LOG.debug("Updating " + entityClass.getName() + " (id: " + entity.getId() + ")");
    Session session = getCurrentSession();
    session.update(entity);
  }

  @Override
  @Transactional
  public final void delete(T entity) {

    LOG.debug("Deleting " + entityClass.getName() + " (id: " + entity.getId() + ")");
    Session session = getCurrentSession();
    session.delete(entity);
  }


  @SuppressWarnings("unchecked")
  public final T findById(final K id) {

    LOG.debug("Finding " + entityClass.getName() + " by id (id: " + id + ")");
    Session session = getCurrentSession();

    Query query = session.createQuery("from " + entityClass.getName()
        + " where id = " + id);

    return (T) query.uniqueResult();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final List<T> findAll() {

    LOG.debug("Finding all " + entityClass.getName() + "'s");

    Session session = getCurrentSession();
    Query query = session.createQuery("from " + entityClass.getName());

    return (List<T>) query.list();
  }

  protected final Session getCurrentSession() {

    return sessionFactory.getCurrentSession();
  }

  protected final Class getEntityClass() {

    final Type type = getClass().getGenericSuperclass();
    if (type instanceof ParameterizedType) {

      return getClassFromTypeArguments((ParameterizedType) type);
    }

    return null;
  }

  protected final Class getClassFromTypeArguments(ParameterizedType type) {

    final Type[] params = type.getActualTypeArguments();
    if ((params != null) && (params.length > 0)) {

      return (Class) params[0];
    }

    return null;
  }
}

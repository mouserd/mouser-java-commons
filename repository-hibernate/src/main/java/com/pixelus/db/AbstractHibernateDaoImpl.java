/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.db;

import com.pixelus.entity.ModelEntity;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class AbstractHibernateDaoImpl<T extends ModelEntity<?>, K>
        implements Dao<T, K> {

    private static final Logger LOG = Logger.getLogger(AbstractHibernateDaoImpl.class);

    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    @Autowired
    public AbstractHibernateDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        entityClass = getEntityClass();
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(final T entity) {

        Session session = currentSession();
        session.save(entity);
        //session.flush();
    }

    @SuppressWarnings("unchecked")
    public T findById(final K id) {

        LOG.info("Finding " + entityClass.getName() + " by id " + id);
        Session session = currentSession();

        Query query = session.createQuery("from " + entityClass.getName()
                + " where id = " + id);

        return (T) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {

        LOG.info("Finding all " + entityClass.getName() + " objects...");

        Session session = currentSession();

        Query query = session.createQuery("from " + entityClass.getName());

        return (List<T>) query.list();
    }

    private Class getEntityClass() {

        final Type genType = getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {

            final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

            if (params != null && params.length >= 1) {

                return (Class) params[0];
            }
        }

        return null;
    }
}

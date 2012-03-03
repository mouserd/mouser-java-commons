package com.pixelus.spi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pixelus.Dao;
import com.pixelus.ModelEntity;

public class AbstractHibernateDaoImpl<T extends ModelEntity<?>, K>
        implements Dao<T, K> {

    private static final Logger LOG = Logger.getLogger(AbstractHibernateDaoImpl.class);

    private SessionFactory sessionFactory;
    private Class entityClass;

    @Autowired
    public AbstractHibernateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        entityClass = getEntityClass();
    }

    protected Session currentSession() {

        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {

        currentSession().save(entity);
    }

    public T findById(K id) {

        LOG.info("Finding " + entityClass.getName() + " by id " + id);
        Session session = currentSession();

        Query query = session.createQuery("from " + entityClass.getName()
                + " where id = " + id);

        return (T) query.uniqueResult();
    }

    @Override
    public List<T> findAll() {

        LOG.info("Finding all " + entityClass.getName() + " objects...");

        Session session = currentSession();

        Query query = session.createQuery("from " + entityClass.getName());

        return query.list();
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
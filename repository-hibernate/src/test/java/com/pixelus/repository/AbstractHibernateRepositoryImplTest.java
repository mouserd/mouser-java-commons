/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

/**
 * @author David Mouser
 */

import com.pixelus.entity.StubEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractHibernateRepositoryImplTest {

    public static final String ENTITY_NAME = "Entity";
    private StubHibernateRepositoryImpl repository;

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    private StubEntity entity;

    @Before
    public void setUp()
          throws Exception {

        repository = new StubHibernateRepositoryImpl(sessionFactory);

        entity = new StubEntity();

        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void saveShouldCallSessionSave() {

        repository.save(entity);
        verify(session).save(entity);
    }

    @Test
    public void updateShouldCallSessionUpdate()
          throws Exception {

        repository.update(entity);
        verify(session).update(entity);
    }

    @Test
    public void deleteShouldCallSessionDelete()
          throws Exception {

        repository.delete(entity);
        verify(session).delete(entity);
    }

    @Test
    public void findByIdShouldQueryById()
          throws Exception {

        Query query = mock(Query.class);
        when(session.createQuery("from " + entity.getClass().getName() + " where id = " +
              "1")).thenReturn(query);
        when(query.uniqueResult()).thenReturn(entity);

        StubEntity returnedEntity = repository.findById(1L);

        assertThat(returnedEntity, is(entity));
        verify(query).uniqueResult();
    }

    @Test
    public void repositoryFindAllShouldCallSessionFind() {

        List<StubEntity> entities = Arrays.asList(entity);
        Query query = mock(Query.class);
        when(session.createQuery("from " + entity.getClass().getName()))
              .thenReturn(query);
        when(query.list()).thenReturn(entities);

        List<StubEntity> returnedEntities = repository.findAll();

        assertThat(returnedEntities, is(entities));
        verify(query).list();
    }
}
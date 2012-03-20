/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.service;

import com.pixelus.entity.ModelEntity;
import com.pixelus.repository.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
 * @author David Mouser
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractServiceImplTest {

    private static final String ENTITY_ID = "entity";
    private AbstractServiceImpl<ModelEntity<String>, String> abstractService;

    @Mock
    private Repository<ModelEntity<String>, String> repository;

    @Before
    public void setUp() {

        abstractService = new AbstractServiceImpl<ModelEntity<String>, String>(repository);
    }

    @Test
    public void saveShouldCallRepository() {

        ModelEntity<String> entity = mock(ModelEntity.class);
        abstractService.save(entity);

        verify(repository).save(entity);
    }

    @Test
    public void updateShouldCallRepository() {

        ModelEntity<String> entity = mock(ModelEntity.class);
        abstractService.update(entity);

        verify(repository).update(entity);
    }

    @Test
    public void deleteShouldCallRepository() {

        ModelEntity<String> entity = mock(ModelEntity.class);
        abstractService.delete(entity);

        verify(repository).delete(entity);
    }

    @Test
    public void findByIdShouldCallRepository() {

        abstractService.findById(ENTITY_ID);

        verify(repository).findById(ENTITY_ID);
    }

    @Test
    public void findAllShouldCallRepository() {

        abstractService.findAll();

        verify(repository).findAll();
    }
}
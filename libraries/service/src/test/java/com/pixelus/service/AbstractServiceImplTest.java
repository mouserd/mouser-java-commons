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

    @Mock
    private ModelEntity<String> mockEntity;

    @Before
    public void setUp() {

        abstractService = new AbstractServiceImpl<>(repository);
    }

    @Test
    public void saveShouldCallRepository() {

        abstractService.save(mockEntity);

        verify(repository).save(mockEntity);
    }

    @Test
    public void updateShouldCallRepository() {

        abstractService.update(mockEntity);

        verify(repository).update(mockEntity);
    }

    @Test
    public void deleteShouldCallRepository() {

        abstractService.delete(mockEntity);

        verify(repository).delete(mockEntity);
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
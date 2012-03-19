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
    private AbstractServiceImpl abstractService;

    @Mock
    private Repository repository;

    @Before
    public void setUp() {

        abstractService = new AbstractServiceImpl<ModelEntity<String>,
              String>(repository);
    }

    @Test
    public void findByIdShouldCallRepository()
          throws Exception {

        abstractService.findById(ENTITY_ID);

        verify(repository).findById(ENTITY_ID);
    }

    @Test
    public void findAllShouldCallRepository() {

        abstractService.findAll();

        verify(repository).findAll();
    }
}
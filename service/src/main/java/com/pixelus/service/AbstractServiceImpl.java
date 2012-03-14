/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.service;

import com.pixelus.repository.Repository;
import com.pixelus.entity.ModelEntity;

import java.util.List;

public class AbstractServiceImpl<T extends ModelEntity<?>, K>
      implements Service<T, K> {

    private Repository<T, K> repository;

    public AbstractServiceImpl(final Repository<T, K> repository) {

        this.repository = repository;
    }

    @Override
    public List<T> findAll() {

        return repository.findAll();
    }

    @Override
    public T findById(final K id) {

        return repository.findById(id);
    }

}
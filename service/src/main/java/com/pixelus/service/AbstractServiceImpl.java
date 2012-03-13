/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.service;

import com.pixelus.db.Dao;
import com.pixelus.entity.ModelEntity;

import java.util.List;

public class AbstractServiceImpl<T extends ModelEntity<?>, K>
        implements Service<T, K> {

    private Dao<T, K> dao;

    public AbstractServiceImpl(final Dao<T, K> dao) {

        this.dao = dao;
    }

    @Override
    public List<T> findAll() {

        return dao.findAll();
    }

    @Override
    public T findById(final K id) {

        return dao.findById(id);
    }

}

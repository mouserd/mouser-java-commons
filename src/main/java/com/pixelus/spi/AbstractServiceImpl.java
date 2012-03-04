package com.pixelus.spi;

import java.util.List;

import com.pixelus.Dao;
import com.pixelus.ModelEntity;
import com.pixelus.Service;

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

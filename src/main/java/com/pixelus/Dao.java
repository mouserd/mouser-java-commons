package com.pixelus;

import java.util.List;

public interface Dao<T extends ModelEntity<?>, K> {

    void save(T entity);

    T findById(K id);

    List<T> findAll();
}

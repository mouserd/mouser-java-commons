package com.pixelus;

import java.util.List;

public interface Service<T extends ModelEntity<?>, K> {

    List<T> findAll();

    T findById(K id);
}

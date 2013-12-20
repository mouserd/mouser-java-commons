/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;

import com.pixelus.entity.ModelEntity;

import java.util.List;

public interface Repository<T extends ModelEntity<?>, K> {

  void save(T entity);

  void update(T entity);

  void delete(T entity);

  T findById(K id);

  List<T> findAll();
}

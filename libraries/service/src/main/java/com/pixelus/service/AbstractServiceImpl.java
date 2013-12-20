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

import java.util.List;

public class AbstractServiceImpl<T extends ModelEntity<?>, K>
    implements Service<T, K> {

  private final Repository<T, K> repository;

  public AbstractServiceImpl(final Repository<T, K> repository) {

    this.repository = repository;
  }

  @Override
  public final List<T> findAll() {

    return repository.findAll();
  }

  @Override
  public final T findById(final K id) {

    return repository.findById(id);
  }

  @Override
  public void save(T entity) {

    repository.save(entity);
  }

  @Override
  public void update(T entity) {

    repository.update(entity);
  }

  @Override
  public void delete(T entity) {

    repository.delete(entity);
  }
}

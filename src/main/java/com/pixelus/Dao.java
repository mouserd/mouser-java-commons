package com.pixelus;

import java.util.List;


public interface Dao<T extends ModelEntity<?>, K> {

	public abstract void save(T entity);

	public abstract T findById(K id);
	
	public abstract List<T> findAll();
}
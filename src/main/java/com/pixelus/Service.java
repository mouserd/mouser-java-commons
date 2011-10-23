package com.pixelus;

import java.util.List;


public interface Service<T extends ModelEntity<?>, K> {

	public abstract List<T> findAll();
	
	public abstract T findById(K id);
}
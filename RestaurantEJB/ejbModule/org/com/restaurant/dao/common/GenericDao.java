package org.com.restaurant.dao.common;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	void save(T entity);
	
	T findById(ID id);
	
	T update(T entity);
	
	void delete(ID id, Class<T> entity);
	
	List<T> findAll();
	
	void flush();
}

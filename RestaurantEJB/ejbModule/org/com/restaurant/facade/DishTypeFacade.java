package org.com.restaurant.facade;

import java.util.List;

import org.com.restaurant.models.DishType;

public interface DishTypeFacade {

	public abstract void save(DishType dishType);
	public abstract DishType findById(int entityID);
	public abstract DishType update(DishType dishType);
	public abstract void delete(DishType dishType);
	public abstract List<DishType> findAll();
	
}

package org.com.restaurant.facade;

import java.util.List;

import org.com.restaurant.models.Dish;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;

public interface DishFacade {

	public abstract void save(Dish dish);
	public abstract Dish findById(int entityID);
	public abstract Dish update(Dish dish);
	public abstract void delete(Dish dish);
	public abstract List<Dish> findAll();
	public abstract List<Dish> findAllDishsByType(DishType dishType);
	public abstract List<Dish> findAllDishsByIngredient(Ingredient ingredient);
	
}

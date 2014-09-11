package org.com.restaurant.facade;

import java.util.List;

import org.com.restaurant.models.Ingredient;

public interface IngredientFacade {

	public abstract void save(Ingredient ingredient);
	public abstract Ingredient findById(int entityID);
	public abstract Ingredient update(Ingredient ingredient);
	public abstract void delete(Ingredient ingredient);
	public abstract List<Ingredient> findAll();
	
}

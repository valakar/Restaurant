package org.com.restaurant.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.com.restaurant.dao.IngredientDao;
import org.com.restaurant.models.Ingredient;

@Stateless
public class IngredientFacadeImp implements IngredientFacade {

	@EJB
	private IngredientDao ingredientDao;
	
	@Override
	public void save(Ingredient ingredient) {
		isIngredientWithAllData(ingredient);
		ingredientDao.save(ingredient);
	}

	@Override
	public Ingredient findById(int entityID) {
		return ingredientDao.findById(entityID);
	}

	@Override
	public Ingredient update(Ingredient ingredient) {
		isIngredientWithAllData(ingredient);
		return ingredientDao.update(ingredient);
	}

	@Override
	public void delete(Ingredient ingredient) {
		ingredientDao.delete(ingredient.getId(), Ingredient.class);
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientDao.findAll();
	}

	private void isIngredientWithAllData(Ingredient ingredient) {
		boolean hasError = false;

		if (ingredient == null) {
			hasError = true;
		}

		if (ingredient.getTitle() == null || "".equals(ingredient.getTitle().trim())) {
			hasError = true;
		}

		if (hasError) {
			throw new IllegalArgumentException(
					"The ingredient is missing data. Check the title it should have value.");
		}
	}
}

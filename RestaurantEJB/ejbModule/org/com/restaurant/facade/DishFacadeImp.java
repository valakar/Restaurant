package org.com.restaurant.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Session;

import org.com.restaurant.dao.DishDao;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;

@Stateless
public class DishFacadeImp implements DishFacade{

	@EJB
	private DishDao dishDao;

	@Override
	public void save(Dish dish) {
		isDishWithAllData(dish);
		dishDao.save(dish);
//		return dishDao.update(dish);
	}

	@Override
	public Dish findById(int entityID) {
		return dishDao.findById(entityID);
	}

	@Override
	public Dish update(Dish dish) {
		isDishWithAllData(dish);
		return dishDao.update(dish);
	}

	@Override
	public void delete(Dish dish) {
		dishDao.delete(dish.getId(), Dish.class);
	}

	@Override
	public List<Dish> findAll() {
		return dishDao.findAll();
	}

	@Override
	public List<Dish> findAllDishsByType(DishType dishType) {
		return dishDao.findAllDishsByType(dishType);
	}

	@Override
	public List<Dish> findAllDishsByIngredient(Ingredient ingredient) {
		return dishDao.findAllDishsByIngredient(ingredient);
	}
	
	private void isDishWithAllData(Dish dish) {
		boolean hasError = false;

		if (dish == null) {
			hasError = true;
		}

		if (dish.getTitle() == null || "".equals(dish.getTitle().trim())) {
			hasError = true;
		}

		if (hasError) {
			throw new IllegalArgumentException(
					"The dish type is missing data. Check the title it should have value.");
		}
	}
}

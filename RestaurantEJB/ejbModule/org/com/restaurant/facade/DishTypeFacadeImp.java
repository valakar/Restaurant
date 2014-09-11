package org.com.restaurant.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.com.restaurant.dao.DishTypeDao;
import org.com.restaurant.models.DishType;

@Stateless
public class DishTypeFacadeImp implements DishTypeFacade {

	@EJB
	private DishTypeDao dishTypeDao;
	
	@Override
	public void save(DishType dishType) {
		isDishTypeWithAllData(dishType);
		dishTypeDao.save(dishType);
	}



	@Override
	public DishType findById(int entityID) {
		return dishTypeDao.findById(entityID);
	}



	@Override
	public DishType update(DishType dishType) {
		isDishTypeWithAllData(dishType);
		return dishTypeDao.update(dishType);
	}



	@Override
	public void delete(DishType dishType) {
		dishTypeDao.delete(dishType.getId(), DishType.class);
	}



	@Override
	public List<DishType> findAll() {
		return dishTypeDao.findAll();
	}

	private void isDishTypeWithAllData(DishType dishType) {
		boolean hasError = false;

		if (dishType == null) {
			hasError = true;
		}

		if (dishType.getTitle() == null || "".equals(dishType.getTitle().trim())) {
			hasError = true;
		}

		if (hasError) {
			throw new IllegalArgumentException(
					"The dish type is missing data. Check the title it should have value.");
		}
	}
}

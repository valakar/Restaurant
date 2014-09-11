package org.com.restaurant.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.com.restaurant.dao.DishDao;
import org.com.restaurant.models.Dish;

@FacesConverter("DishConverter")
public class DishConverter implements Converter {

	@EJB
	DishDao dishDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Dish dish = (new Dish(Integer.parseInt(arg2)));
		dish = dishDao.findById(dish.getId());
		return dish;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		if (arg2 instanceof Ingredient) {
//		    return ((Ingredient) arg2).getId().toString();
//		}
//		return null;
		
		Dish dish = (Dish) arg2;
	    return dish.getId() != null ? String.valueOf(dish.getId()) : null;
	}

}

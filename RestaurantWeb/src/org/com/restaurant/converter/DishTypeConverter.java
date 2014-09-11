package org.com.restaurant.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.com.restaurant.dao.DishTypeDao;
import org.com.restaurant.models.DishType;

@FacesConverter("DishTypeConverter")
public class DishTypeConverter implements Converter {

	@EJB
	DishTypeDao dishTypeDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		DishType dishType = (new DishType(Integer.parseInt(arg2)));
		dishType = dishTypeDao.findById(dishType.getId());
		return dishType;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		if (arg2 instanceof Ingredient) {
//		    return ((Ingredient) arg2).getId().toString();
//		}
//		return null;
		
		DishType dishType = (DishType) arg2;
	    return dishType.getId() != null ? String.valueOf(dishType.getId()) : null;
	}

}

package org.com.restaurant.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.com.restaurant.dao.IngredientDao;
import org.com.restaurant.models.Ingredient;

@FacesConverter("IngredientConverter")
public class IngredientConverter implements Converter {

	@EJB
	IngredientDao ingredientDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Ingredient ingredient = (new Ingredient(Integer.parseInt(arg2)));

		System.err.println("TESTT " + ingredient);
		ingredient = ingredientDao.findById(ingredient.getId());
		System.err.println("TESTT2 " + ingredient);
//		int industryIndex = IndustryData.getIndustries().indexOf(industry);
//		if (industryIndex >= 0)
//		{
//		    return IndustryData.getIndustries().get(industryIndex);
//		}
		return ingredient;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
//		if (arg2 instanceof Ingredient) {
//		    return ((Ingredient) arg2).getId().toString();
//		}
//		return null;
		
		Ingredient ingr = (Ingredient) arg2;
	    return ingr.getId() != null ? String.valueOf(ingr.getId()) : null;
	}

}

package org.com.restaurant.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.com.restaurant.facade.DishFacade;
import org.com.restaurant.facade.IngredientFacade;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class DishBean {

	@EJB
	private DishFacade dishFacade;
	
	private static final String OPEN_DISH = "dish";
	private static final String CREATE_DISH = "createDish";
    private static final String DELETE_DISH = "deleteDish";
    private static final String UPDATE_DISH = "updateDish";
    private static final String LIST_ALL_DISHS = "menuDish";//"listAllDishs";
    private static final String LIST_ALL_DISHS_INGR = "menuDishIngr";
    private static final String LIST_ALL_TYPES = "menu";
    private static final String LIST_ALL_INGR = "menuIngr";
    private static final String LIST_ALL = "listAllDishs";
    private static final String STAY_IN_THE_SAME_PAGE = null;
    

    private Dish dish;
    private DishType dishType;
    private Ingredient ingredient;

	public Dish getDish() {
		
		if(dish == null){
			dish = new Dish();
		}
		
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
    
	public DishType getDishType() {
		return dishType;
	}
	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	public List<Dish> getAllDishs() {
		return dishFacade.findAll();
	}
	
	public List<Dish> getAllDishsByType() {
		return dishFacade.findAllDishsByType(dishType);
	}
	
	public String getAllIngredientsForDish() {
		String temp = null;
		for (Ingredient ingredient : dish.getIngredients()) {
			if (temp == null) {
				temp = ingredient.getTitle();
			} else {
				temp += ", " + ingredient.getTitle();
			}
		}
		return temp;
	}
	
	public String getAllDishTypesForDish() {
		String temp = null;
		for (DishType dishType : dish.getDishtypes()) {
			if (temp == null) {
				temp = dishType.getTitle();
			} else {
				temp += ", " + dishType.getTitle();
			}
		}
		return temp;
	}
	
	public List<Dish> getAllDishsByIngredient() {
		return dishFacade.findAllDishsByIngredient(ingredient);
	}
	
	public String updateDishStart() {
		return UPDATE_DISH;
	}
	
	public String updateDishEnd() {
		try {
//			dishFacade.save(dish);
			dishFacade.update(dish);
			dish = new Dish();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check dish inputs");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
        return LIST_ALL;
	}
	
	public String deleteDishStart() {
		return DELETE_DISH;
	}
	
	public String deleteDishEnd() {
		try {
			dishFacade.delete(dish);
			dish = new Dish();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Delete");
        return LIST_ALL;
	}
	
	public String createDishStart() {
		dish = new Dish();
		return CREATE_DISH;
	}
	
	public String createDishEnd() {
		try {
			dishFacade.save(dish);
			dish = new Dish();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Such dish is already in a system. Choose another title please.");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
        return LIST_ALL;
	}
	
	public String listAllDishs() {
		return LIST_ALL_DISHS;
	}
	public String listAll() {
		return LIST_ALL;
	}
	
	public String listAllDishsIngr() {
		return LIST_ALL_DISHS_INGR;
	}
	public String listAllTypes() {
		return LIST_ALL_TYPES;
	}
	public String listAllIngrs() {
		return LIST_ALL_INGR;
	}
	
	public String openDish() {
		return OPEN_DISH;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        try {
        	dish.setImages(event.getFile().getContents());
        	FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The files were not uploaded!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}
	}
	
	public List<Ingredient> getIngredients(Set<Ingredient> set) {
		  return new ArrayList<Ingredient>(set);
	}
	
	public List<DishType> getDishTypes(Set<DishType> set) {
		  return new ArrayList<DishType>(set);
	}
	
	private void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
 
    private void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }
}


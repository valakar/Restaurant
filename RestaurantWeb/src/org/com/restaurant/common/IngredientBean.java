package org.com.restaurant.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.com.restaurant.facade.IngredientFacade;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class IngredientBean {

	@EJB
	private IngredientFacade ingredientFacade;
	
	private static final String CREATE_INGREDIENT = "createIngredient";
    private static final String DELETE_INGREDIENT = "deleteIngredient";
    private static final String UPDATE_INGREDIENT = "updateIngredient";
    private static final String LIST_ALL_INGREDIENTS = "menuIngr";
    private static final String STAY_IN_THE_SAME_PAGE = null;
    
    private Ingredient ingredient;

    public Ingredient getIngredient() {
		
		if(ingredient == null){
			ingredient = new Ingredient();
		}
		
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<Ingredient> getAllIngredients() {
		return ingredientFacade.findAll();
	}
	
//	public List<Ingredient> getListAsSet(Set<Ingredient> set) {
//	  return new ArrayList<Ingredient>(set);
//}
//
	
	public List<Ingredient> getAllIngredientsAsSet() {
		return new ArrayList<Ingredient>(ingredientFacade.findAll());
	}
	
	public String getAllIngredientsTitles() {
		String temp = null;
		for (Ingredient ingredient : ingredientFacade.findAll()) {
			if (temp == null) {
				temp = ingredient.getTitle();
			} else {
				temp += ", " + ingredient.getTitle();
			}
		}
		return temp;
	}
	
	public String updateIngredientStart() {
		return UPDATE_INGREDIENT;
	}
	
	public String updateIngredientEnd() {
		try {
			ingredientFacade.update(ingredient);
			ingredient = new Ingredient();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check ingredient inputs");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
        return LIST_ALL_INGREDIENTS;
	}
	
	public String deleteIngredientStart() {
		return DELETE_INGREDIENT;
	}
	
	public String deleteIngredientEnd() {
		try {
			ingredientFacade.delete(ingredient);
			ingredient = new Ingredient();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Delete");
        return LIST_ALL_INGREDIENTS;
	}
	
	public String createIngredientStart() {
		ingredient = new Ingredient();
		return CREATE_INGREDIENT;
	}
	
	public String createIngredientEnd() {
		try {
			ingredientFacade.save(ingredient);
			ingredient = new Ingredient();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Such ingredient is already in a system. Choose another title please.");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
        return LIST_ALL_INGREDIENTS;
	}
	
	public String listAllIngredients() {
		return LIST_ALL_INGREDIENTS;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        try {
        	ingredient.setImages(event.getFile().getContents());
        	FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The files were not uploaded!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}
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

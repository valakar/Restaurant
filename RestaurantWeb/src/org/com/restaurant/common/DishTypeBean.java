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

import org.com.restaurant.facade.DishTypeFacade;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class DishTypeBean {

	@EJB
	private DishTypeFacade dishTypeFacade;
	
	private static final String CREATE_DISHTYPE = "createDishType";
    private static final String DELETE_DISHTYPE = "deleteDishType";
    private static final String UPDATE_DISHTYPE = "updateDishType";
    private static final String LIST_ALL_DISHTYPES = "menu";
    private static final String STAY_IN_THE_SAME_PAGE = null;

    private DishType dishType;

	public DishType getDishType() {
		
		if(dishType == null){
			dishType = new DishType();
		}
		
		return dishType;
	}

	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}
    
	public List<DishType> getAllDishTypes() {
		return dishTypeFacade.findAll();
	}
	
//	public List<Ingredient> getListAsSet(Set<Ingredient> set) {
//		  return new ArrayList<Ingredient>(set);
//	}
//	
	
	public List<DishType> getAllDishTypesAsSet() {
		return new ArrayList<DishType>(dishTypeFacade.findAll());
	}
	

	
	public String updateDishTypeStart() {
		return UPDATE_DISHTYPE;
	}
	
	public String updateDishTypeEnd() {
		try {
			dishTypeFacade.update(dishType);
			dishType = new DishType();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check dish type inputs");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
        return LIST_ALL_DISHTYPES;
	}
	
	public String deleteDishTypeStart() {
		return DELETE_DISHTYPE;
	}
	
	public String deleteDishTypeEnd() {
		try {
			dishTypeFacade.delete(dishType);
			dishType = new DishType();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Delete");
        return LIST_ALL_DISHTYPES;
	}
	
	public String createDishTypeStart() {
		dishType = new DishType();
		return CREATE_DISHTYPE;
	}
	
	public String createDishTypeEnd() {
		try {
			dishTypeFacade.save(dishType);
			dishType = new DishType();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Such dish type is already in a system. Choose another title please.");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
        return LIST_ALL_DISHTYPES;
	}
	
	public String listAllDishTypes() {
		return LIST_ALL_DISHTYPES;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        try {
        	dishType.setImages(event.getFile().getContents());
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

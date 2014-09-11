package org.com.restaurant.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.com.restaurant.facade.UserFacade;
import org.com.restaurant.models.Roles;
import org.com.restaurant.models.User;
import org.jboss.security.auth.spi.Util;

@ManagedBean
@RequestScoped
public class UserBean {

	@EJB
	private UserFacade userFacade;

	private static final String CREATE_USER = "createUser";
    private static final String DELETE_USER = "deleteUser";
    private static final String UPDATE_USER = "updateUser";
    private static final String LIST_ALL_USERS = "listAllUsers";
    private static final String INDEX = "done";
    private static final String STAY_IN_THE_SAME_PAGE = null;

    private User user;
    private String passAgain;

	public User getUser() {
		
		if(user == null){
			user = new User();
		}
		
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
	public String getPassAgain() {
		return passAgain;
	}
	public void setPassAgain(String passAgain) {
		this.passAgain = passAgain;
	}
	public List<User> getAllUsers() {
		return userFacade.findAll();
	}
	
	public String updateUserStart() {
		return UPDATE_USER;
	}
	
	public String updateUserEnd() {
		try {
			userFacade.update(user);
			user = new User();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check user inputs");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
        return INDEX;
	}
	
	public String deleteUserStart() {
		return DELETE_USER;
	}
	
	public String deleteUserEnd() {
		try {
			userFacade.delete(user);
			user = new User();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Delete");
        return LIST_ALL_USERS;
	}
	
	public String createUserStart() {
		user = new User();
		return CREATE_USER;
	}
	
	public String createUserEnd() {
		String pass = user.getPassword();
		if (!pass.equals(passAgain)) {
			sendErrorMessageToUser("Passwords doesn't matches");
            return STAY_IN_THE_SAME_PAGE;
		}
		try {
			user.setPassword(getHash(user.getPassword()));
			Set<Roles> roles = new HashSet<Roles>();;
			roles.add(new Roles(getUser().getEmail(), "user"));
			user.setRoles(roles);
			userFacade.save(user);
			user = new User();
		} catch (Exception e) {
			sendErrorMessageToUser("Error. Such email is already in system. Choose another please.");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
        return INDEX;
	}
	
	public String listAllUsers() {
		return LIST_ALL_USERS;
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

    public String getHash(String item) {
		return Util.createPasswordHash("SHA", Util.BASE64_ENCODING, null, null,
				item);
	}
}

package org.com.restaurant.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.com.restaurant.facade.UserFacade;
import org.com.restaurant.models.User;

@SessionScoped
@ManagedBean
public class UserMB {

	private User user;
	
	@EJB
	private UserFacade userFacade;
	
	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			String userEmail = context.getUserPrincipal().getName();
			
			user = userFacade.findUserByEmail(userEmail);
		}
		
		return user;
	}
	
	public boolean checkUser() {
		if (user == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean isUserAdmin(){
		return getRequest().isUserInRole("admin");
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}

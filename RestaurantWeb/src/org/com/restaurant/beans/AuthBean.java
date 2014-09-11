package org.com.restaurant.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.com.restaurant.facade.UserFacade;
import org.com.restaurant.models.User;
import org.hibernate.validator.constraints.Email;
import org.jboss.security.auth.spi.Util;

@ManagedBean
@SessionScoped
public class AuthBean {

	@EJB
	private UserFacade userFacade;

	private HttpServletRequest request;

	private User user;

	@Email
	@Size(min = 6, max = 40)
	private String email;

	@Size(min = 3, max = 40)
	private String password;

	@Size(min = 3, max = 40)
	private String passwordAgain;

	public String signin() {
		try {
//			request.login(email, password);
			getRequest().login(email, getHash(password));
			this.user = getUser();
			return "index";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Login failed", "wrong login or password"));
		}
		return null;
	}

	public String getHash(String item) {
		return Util.createPasswordHash("SHA", Util.BASE64_ENCODING, null, null,
				item);
	}

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String userEmail = context.getUserPrincipal().getName();

			user = userFacade.findUserByEmail(userEmail);
		}

		return user;
		//
		// String email = FacesContext.getCurrentInstance().getExternalContext()
		// .getRemoteUser();
		// return userFacade.findUserByEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

//	public static HttpServletRequest getRequest() {
//		Object request = FacesContext.getCurrentInstance().getExternalContext()
//				.getRequest();
//		return (HttpServletRequest) request;
//	}
	
	private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

	public boolean checkUser() {
		if (user == null) {
			return false;
		}

		return true;
	}

	public String logOut() {
		this.user = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		if (isAuthenticated())
			try {
				getRequest().logout();
			} catch (ServletException e) {
				return null; 
			}
		return "logout";

		// getRequest().getSession().invalidate();
		// return "logout";
	}

	public boolean isAuthenticated() {
		return getRequest().getUserPrincipal() != null;
	}
}

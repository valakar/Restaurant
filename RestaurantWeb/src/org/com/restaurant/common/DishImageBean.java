package org.com.restaurant.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.com.restaurant.facade.DishFacade;
import org.com.restaurant.models.Dish;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class DishImageBean {

	@EJB
	DishFacade dishFacade;
	
	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			String dishId = context.getExternalContext()
					.getRequestParameterMap().get("id");
			Dish dish = dishFacade.findById(Integer
					.valueOf(dishId));
			return new DefaultStreamedContent(new ByteArrayInputStream(
					dish.getImages()));
		}
	}
}

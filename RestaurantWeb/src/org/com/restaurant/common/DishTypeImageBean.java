package org.com.restaurant.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.com.restaurant.facade.DishTypeFacade;
import org.com.restaurant.models.DishType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class DishTypeImageBean {

	@EJB
	private DishTypeFacade dishTypeFacade;
	
	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			String dishTypeId = context.getExternalContext()
					.getRequestParameterMap().get("id");
			DishType dishType = dishTypeFacade.findById(Integer
					.valueOf(dishTypeId));
			return new DefaultStreamedContent(new ByteArrayInputStream(
					dishType.getImages()));
		}
	}
}

package org.com.restaurant.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.com.restaurant.facade.IngredientFacade;
import org.com.restaurant.models.Ingredient;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class IngredientImageBean {

	@EJB
	private IngredientFacade ingredientFacade;

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			String ingredientId = context.getExternalContext()
					.getRequestParameterMap().get("id");
			Ingredient ingredient = ingredientFacade.findById(Integer
					.valueOf(ingredientId));
			return new DefaultStreamedContent(new ByteArrayInputStream(
					ingredient.getImages()));
		}
	}
}

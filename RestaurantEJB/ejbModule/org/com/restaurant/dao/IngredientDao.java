package org.com.restaurant.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.Ingredient;

@Stateless
public class IngredientDao extends GenericJpaDao<Ingredient, Integer> {

	public IngredientDao() {
		super(Ingredient.class);
	}

//	@Override
//	public Ingredient findById(Integer id) {
////		TypedQuery<Ingredient> query = getEntityManager()
////				.createQuery("SELECT i FROM Ingredient i WHERE i.id IN :iid", Ingredient.class);
////				query.setParameter("iid", id);
////				return query.getSingleResult();
////		
//		Ingredient ing = super.findById(id);
//		int length = ing.getImages().length;
//		return ing;
//	}
	
	
}

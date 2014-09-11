package org.com.restaurant.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;

@Stateless
public class DishTypeDao extends GenericJpaDao<DishType, Integer> {

	public DishTypeDao() {
		super(DishType.class);
	}

	@Override
	public DishType findById(Integer id) {
//		TypedQuery<DishType> query = getEntityManager()
//				.createQuery("SELECT i FROM DishType i WHERE i.id IN :iid", DishType.class);
//				query.setParameter("iid", id);
//				return query.getSingleResult();
		DishType type= super.findById(id);
		int length = type.getImages().length;
		return type;
	}
	
	
}

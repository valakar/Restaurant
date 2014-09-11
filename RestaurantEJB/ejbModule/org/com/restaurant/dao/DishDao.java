package org.com.restaurant.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.DishType;
import org.com.restaurant.models.Ingredient;

@Stateless
public class DishDao extends GenericJpaDao<Dish, Integer>  {

	public DishDao() {
		super(Dish.class);
	}

	@Override
	public void save(Dish entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public Dish update(Dish entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}


	@Override
	public void delete(Integer id, Class<Dish> entity) {
		// TODO Auto-generated method stub
		super.delete(id, entity);
	}

	public List<Dish> findAllDishsByType(DishType entity) {
		
		TypedQuery<Dish> query = getEntityManager()
		.createQuery("SELECT d FROM Dish d JOIN d.dishtypes t WHERE t IN :dishtype", Dish.class);
		query.setParameter("dishtype", entity);
		return query.getResultList();
		
    }
	
	public List<Dish> findAllDishsByIngredient(Ingredient entity) {
		
		TypedQuery<Dish> query = getEntityManager()
		.createQuery("SELECT d FROM Dish d JOIN d.ingredients i WHERE i IN :ingredient", Dish.class);
		query.setParameter("ingredient", entity);
		return query.getResultList();
		
    }
	
}

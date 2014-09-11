package org.com.restaurant.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.Order;
import org.com.restaurant.models.User;

@Stateless
public class OrderDao extends GenericJpaDao<Order, Integer> {

	public OrderDao() {
		super(Order.class);
	}
	
	public List<Order> findAllByUser(User user) {
		
		TypedQuery<Order> query = getEntityManager()
				.createQuery("SELECT o FROM Order o WHERE o.user IN :uid ORDER BY o.id DESC", Order.class);
				query.setParameter("uid", user);
				return query.getResultList();
		
	}

	@Override
	public void save(Order entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public Order update(Order entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(Integer id, Class<Order> entity) {
		// TODO Auto-generated method stub
		super.delete(id, entity);
	}

	@Override
	public List<Order> findAll() {
		TypedQuery<Order> query = getEntityManager()
				.createQuery("SELECT o FROM Order o ORDER BY o.id DESC", Order.class);
				return query.getResultList();
	}

	
	
}

package org.com.restaurant.facade;

import java.util.List;

import javax.ejb.Local;

import org.com.restaurant.models.User;

@Local
public interface UserFacade {
	
	public abstract void save(User user);
	public abstract User findById(int entityID);
	public abstract User update(User user);
	public abstract void delete(User user);
	public abstract List<User> findAll();
	public User findUserByEmail(String email);
	
}

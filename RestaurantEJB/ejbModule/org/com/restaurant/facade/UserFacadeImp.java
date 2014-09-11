package org.com.restaurant.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.com.restaurant.dao.UserDao;
import org.com.restaurant.models.User;

@Stateless
public class UserFacadeImp implements UserFacade {

	@EJB
	private UserDao userDao;
	
	@Override
	public void save(User user) {
		isUserWithAllData(user);
		userDao.save(user);
	}

	@Override
	public User findById(int entityID) {
		return userDao.findById(entityID);
	}

	@Override
	public User update(User user) {
		isUserWithAllData(user);
		return userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user.getId(), User.class);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}


	private void isUserWithAllData(User user) {
		boolean hasError = false;

		if (user == null) {
			hasError = true;
		}

		if (user.getEmail() == null || "".equals(user.getEmail().trim())) {
			hasError = true;
		}

		if (user.getPassword() == null) {
			hasError = true;
		}

		if (hasError) {
			throw new IllegalArgumentException(
					"The user is missing data. Check the email and password, they should have value.");
		}
	}
}

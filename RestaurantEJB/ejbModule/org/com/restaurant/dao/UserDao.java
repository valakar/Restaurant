package org.com.restaurant.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.User;

@Stateless
public class UserDao extends GenericJpaDao<User, Integer> {

//	public UserDao(Class<User> persistentClass) {
//		super(persistentClass);
//	}
	
	public UserDao() {
		super(User.class);
	}

	public User findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);    
 
        return super.findOneResult(User.FIND_BY_EMAIL, parameters);
    }

}

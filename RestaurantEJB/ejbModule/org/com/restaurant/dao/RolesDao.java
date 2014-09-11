package org.com.restaurant.dao;

import javax.ejb.Stateless;

import org.com.restaurant.dao.common.GenericJpaDao;
import org.com.restaurant.models.Roles;

@Stateless
public class RolesDao extends GenericJpaDao<Roles, Integer> {

	public RolesDao() {
		super(Roles.class);
	}

}

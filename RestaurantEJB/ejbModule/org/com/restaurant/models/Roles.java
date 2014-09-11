package org.com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String user_role;
	
	public Roles() {
		super();
	}
	public Roles(String email) {
		super();
		this.email = email;
	}
	public Roles(String email, String role) {
		super();
		this.email = email;
		this.user_role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
//	
//	@Override
//	public int hashCode() {
//		return getId();
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Roles){
//			Roles role = (Roles) obj;
//			return role.getId() == getId();
//		}
//		return false;
//	}
}

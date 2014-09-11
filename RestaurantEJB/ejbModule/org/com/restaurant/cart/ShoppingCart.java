package org.com.restaurant.cart;

import java.util.HashMap;

import javax.ejb.Local;

import org.com.restaurant.models.Dish;

@Local
public interface ShoppingCart {
	public void buy(Dish dish, Integer amount);
	
	public void checkout();
	
	public HashMap<Dish, Integer> getCartContents();
}

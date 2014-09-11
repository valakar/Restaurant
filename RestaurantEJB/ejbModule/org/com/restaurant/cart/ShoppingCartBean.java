package org.com.restaurant.cart;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.com.restaurant.models.Dish;

@Stateful
public class ShoppingCartBean implements ShoppingCart {

	private final static Logger LOGGER = Logger.getLogger(ShoppingCartBean.class.toString());

	private HashMap<Dish, Integer> cart = new HashMap<Dish, Integer>();

	@Override
	public void buy(Dish dish, Integer amount) {
		if (cart.containsKey(dish)) {
			int currentAmount = cart.get(dish);
			currentAmount += amount;
			cart.put(dish, currentAmount);
		} else {
			cart.put(dish, amount);
		}
	}

	@Override
//	@Remove
	public void checkout() {
		cart = new HashMap<Dish, Integer>();
		LOGGER.info("checkout");
//		System.err.println("checkout");
	}

	@Override
	public HashMap<Dish, Integer> getCartContents() {
		return cart;
	}
}

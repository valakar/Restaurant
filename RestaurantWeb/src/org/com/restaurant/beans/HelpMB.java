package org.com.restaurant.beans;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class HelpMB {

	List<Integer> places = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
	private static Map<String, Integer> status;
	
	static {
		status = new LinkedHashMap<String, Integer>();
		status.put("Payment Awaiting", 0);
		status.put("Paid", 1);
		status.put("Ready", 2);
		status.put("Canceled", 3);
	}
	
	public String status(Integer temp) {
		for (Entry<String, Integer> entry : status.entrySet()) {
			if (entry.getValue().equals(temp)){
				return entry.getKey();
			}
		}
		return null;
	}
	
	public List<Integer> getPlaces() {
		return places;
	}

	public Map<String, Integer> getStatus() {
		return status;
	}

	public void setStatus(Map<String, Integer> status) {
		this.status = status;
	}

	public String login() {
		return "login";
	}
	
	public String registration() {
		return "createUser";
	}
	
	public String cart() {
		return "shoppingCart";
	}
	
	public String main() {
		return "index";
	}
	
	public String menu() {
		return "listAllDishs";
	}
	
	public String orders() {
		return "listAllOrders";
	}
	
	public String ordersAll() {
		return "listAllOrdersForALL";
	}
	
	public String dishtypes() {
		return "listAllDishTypes";
	}
	
	public String ingredients() {
		return "listAllIngredients";
	}
	
	public String users() {
		return "listAllUsers";
	}
}

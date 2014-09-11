package org.com.restaurant.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.com.restaurant.dao.OrderDao;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.Order;
import org.com.restaurant.models.OrderItem;
import org.com.restaurant.models.User;


@SessionScoped
@ManagedBean
public class ShoppingCartMB {
	
	private static final String SHOW_CART = "shoppingCart";
	private static final String CREATE_ORDER = "createOrder";
	private static final String LIST_ALL_ORDERS = "listAllOrders";
	private static final String SUCCESS = "done";
	private static final String STAY_IN_THE_SAME_PAGE = null;
	private final static Logger LOGGER = Logger.getLogger(ShoppingCartMB.class.toString());

	@EJB
	private OrderDao orderDao;
//	@EJB
//	private DishFacade dishFacade;
	
	private User user;
	
	private Order order;
	private Dish dish;
	private Integer amount;


	private HashMap<Dish, Integer> cart = new HashMap<Dish, Integer>();

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void buy(Dish dish, Integer amount) {
		if (cart.containsKey(dish)) {
			int currentAmount = cart.get(dish);
			currentAmount += amount;
			cart.put(dish, currentAmount);
		} else {
			cart.put(dish, amount);
		}
	}
	
	public void delete(Dish dish) {
		cart.remove(dish);
	}
	
	public Dish getDish() {
		if(dish == null){
			dish = new Dish();
		}
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Order getOrder() {
		if(order == null){
			order = new Order();
		}
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void addDishs() {
		buy(dish, amount);
	}
	
	public String addDish() {
		buy(dish, 1);
		return SHOW_CART;
	}
	
	public String delDish() {
		delete(dish);
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public User user() {
		return user;
	}
	
	public String clearCart() {
		cart = new HashMap<Dish, Integer>();
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public boolean emptyCart() {
		if (cart.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public BigDecimal orderSummary() {
		
		BigDecimal summary = new BigDecimal(0);
		BigDecimal temp = new BigDecimal(0);
		for (Entry<Dish, Integer> entry : cart.entrySet()) {
			LOGGER.info("PRICE " + entry.getKey().getPrice());
			LOGGER.info("AMOUNT " + entry.getValue());
			temp = entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
			LOGGER.info("MULTIPLE " + temp);
			summary = summary.add(temp);
//			summary.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
			
		}
		LOGGER.info("SUMMARY " + summary);
		return summary;
		
	}
	
	public String createOrderStart() {
		BigDecimal summary = new BigDecimal(0);
		BigDecimal temp = new BigDecimal(0);
		for (Entry<Dish, Integer> entry : cart.entrySet()) {
			temp = entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
			summary = summary.add(temp);
		}
		getOrder().setSummary(summary);
		return CREATE_ORDER;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String checkout() {
		
		LOGGER.info("checkout");
		LOGGER.info(cart.toString());
		
//		orderDao.save(order.setUser(user));
		
		LOGGER.info("user " + user);
//		Order order = new Order();
//		order.setComment("comment");
		order.setOrdertime(new Date());
//		order.setTime(new Date());
//		order.setPlace(10);
		order.setUser(user);
		LOGGER.info("order " + order);
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		for (Entry<Dish, Integer> entry : cart.entrySet()) {
			OrderItem item = new OrderItem(entry.getValue(), entry.getKey().getPrice(), entry.getKey());
			LOGGER.info("item " + item);
//			LOGGER.info(item.toString());
			orderItems.add(item);
			
		}
		for (OrderItem orderItem : orderItems) {
			LOGGER.info("OrderItem " + orderItem.toString());
		}
		order.setOrderItems(orderItems);
//		LOGGER.info("OrderItems  " + order);
//		order.setOrderItems(orderItems);
//		LOGGER.info(order.toString());
		try {
			LOGGER.info(order.toString());
			orderDao.save(order);
			cart = new HashMap<Dish, Integer>();
			order = new Order();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Something wrong!.");
            return STAY_IN_THE_SAME_PAGE;
		}
		
		sendInfoMessageToUser("Operation Complete: Create");
//		shoppingCartBean.checkout();
		return SUCCESS;
	}
	
	public ArrayList<Entry<Dish, Integer>> getCart() {
		return new ArrayList<Entry<Dish, Integer>>(cart.entrySet());
//		return shoppingCartBean.getCartContents();
	}
	
	private void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
 
    private void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    private FacesContext getContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }
}

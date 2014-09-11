package org.com.restaurant.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.com.restaurant.dao.OrderDao;
import org.com.restaurant.models.Dish;
import org.com.restaurant.models.Order;
import org.com.restaurant.models.OrderItem;
import org.com.restaurant.models.User;

@ManagedBean
@SessionScoped
public class OrderBean {
	
	@EJB
	private OrderDao orderDao;
	
	private static final String LIST_ALL_ORDERS = "listAllOrders";
	private static final String LIST_ALL = "listAllOrdersForALL";
	private static final String SHOW_ORDER = "showOrder";
	private static final String UPDATE_ORDER = "updateOrder";
	private static final String STAY_IN_THE_SAME_PAGE = null;
	
	private Order order;
	private OrderItem orderItem;
//	private User user;

	public Order getOrder() {
		
		if(order == null){
			order = new Order();
		}
		
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//	public User getUser() {
//		return user;
//	}
	
	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public List<Order> getAllOrdersByUser(User user) {
		return orderDao.findAllByUser(user);
	}
	
	public List<Order> getAllOrders() {
		return orderDao.findAll();
	}
	
	public String listAllOrders() {
		return LIST_ALL_ORDERS;
	}
	
	public String listAllOrdersForALL() {
		return LIST_ALL;
	}
	
	public String showOrder() {
		return SHOW_ORDER;
	}
	
	public String updateOrderStart() {
		return UPDATE_ORDER;
	}
	
	public void deleteOrderItem(OrderItem oi) {
		order.getOrderItems().remove(oi);
	}
	
	
	public String updateOrderEnd() {
		try {
			BigDecimal summary = new BigDecimal(0);
			BigDecimal temp = new BigDecimal(0);

			for (OrderItem oi : order.getOrderItems()) {
				temp = oi.getPrice().multiply(new BigDecimal(oi.getAmount()));
				summary = summary.add(temp);
			}
			order.setSummary(summary);
				
			orderDao.update(order);
			order = new Order();
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check order inputs");
            return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Update");
        return LIST_ALL;
	}
	
	public ArrayList<OrderItem> orderItemsAsList() {
		return new ArrayList<OrderItem>(order.getOrderItems());
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

package org.com.restaurant.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer amount;
	private BigDecimal price;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=Dish.class)
	@JoinColumn(name = "dish_id")
	private Dish dish;
	
	public OrderItem() {
		super();
	}
	
	public OrderItem(Integer amount, BigDecimal price, Dish dish) {
		super();
		this.amount = amount;
		this.price = price;
		this.dish = dish;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	   @Override
	    public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dish == null) ? 0 : dish.hashCode());
		return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
		if (this == obj)
		    return true;
		if (obj == null)
		    return false;
		if (getClass() != obj.getClass())
		    return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
		    if (other.id != null)
			return false;
		} else if (!id.equals(other.id))
		    return false;
		return true;
	    }
	    
	    @Override
	    public String toString() {
			
	    	return "Id: " + this.getId() + " Price: " + this.getPrice() + " Amount: " + this.getAmount() + " Dish: " + this.getDish().getTitle();
		}

}

package org.com.restaurant.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer place;
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ordertime;
	private String comment;
	@Basic(fetch = FetchType.EAGER)
	private BigDecimal summary;
	private Integer status = 0;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(fetch = FetchType.EAGER, targetEntity=OrderItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	public Order() {
		super();
	}
	
	public Order(User user) {
		super();
		this.user = user;
	}
	
	public Order(Integer place, Date time, Date ordertime, String comment,
			User user) {
		super();
		this.place = place;
		this.time = time;
		this.ordertime = ordertime;
		this.comment = comment;
		this.user = user;
	}
	
	public Order(Integer place, Date time, Date ordertime, String comment,
			User user, Set<OrderItem> orderItems) {
		super();
		this.place = place;
		this.time = time;
		this.ordertime = ordertime;
		this.comment = comment;
		this.user = user;
		this.orderItems = orderItems;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlace() {
		return place;
	}
	public void setPlace(Integer place) {
		this.place = place;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public BigDecimal getSummary() {
		return summary;
	}

	public void setSummary(BigDecimal summary) {
		this.summary = summary;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());//????
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Id: " + this.getId() + " Place: " + this.getPlace()
				+ " Time: " + this.getTime() + " User: "
				+ this.getUser().getEmail() + " OrderItems: "
						+ this.getOrderItems();
	}
}

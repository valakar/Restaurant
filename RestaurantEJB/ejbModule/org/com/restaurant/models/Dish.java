package org.com.restaurant.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Dish implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_id", nullable = false)
	private Integer id;
	private String title;
	private String description;
	private Integer size;
	private BigDecimal price;
	@Lob
	private byte[] images;
	@ManyToMany(fetch = FetchType.EAGER, targetEntity=Ingredient.class)
	@JoinTable(name = "dish_ingredient", joinColumns = {
			@JoinColumn(name = "dish_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "ingredient_id",
					nullable = false, updatable = false) })
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	@ManyToMany(fetch = FetchType.EAGER, targetEntity=DishType.class)
	@JoinTable(name = "dish_dishtype", joinColumns = {
			@JoinColumn(name = "dish_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "dishtype_id",
					nullable = false, updatable = false) })
	private Set<DishType> dishtypes = new HashSet<DishType>();
	
	public Dish() {
		super();
	}
	public Dish(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public byte[] getImages() {
		return images;
	}
	public void setImages(byte[] images) {
		this.images = images;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Set<DishType> getDishtypes() {
		return dishtypes;
	}
	public void setDishtypes(Set<DishType> dishtypes) {
		this.dishtypes = dishtypes;
	}
	
	@Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	Dish other = (Dish) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
		
    	return "Id: " + this.getId() + " Title: " + this.getTitle() + " Desc: " + this.getDescription() + " Price: " + this.getPrice();
	}
}

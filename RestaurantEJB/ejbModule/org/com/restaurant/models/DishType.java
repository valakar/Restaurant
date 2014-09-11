package org.com.restaurant.models;

import java.io.Serializable;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class DishType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	private String title;
	private String description;
	@Lob
	private byte[] images;
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishtypes", targetEntity=Dish.class)
//	private Set<Dish> dishs = new HashSet<Dish>();
	
	public DishType() {
	}
	
	public DishType(Integer id) {
		super();
		this.id = id;
	}
	
	public DishType(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	public DishType(String title, String description, byte[] images) {
		super();
		this.title = title;
		this.description = description;
		this.images = images;
	}
	public Integer getId() {
		return id;
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
	public byte[] getImages() {
		return images;
	}
	public void setImages(byte[] images) {
		this.images = images;
	}
	
//	public Set<Dish> getDishs() {
//		return dishs;
//	}
//
//	public void setDishs(Set<Dish> dishs) {
//		this.dishs = dishs;
//	}

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
	DishType other = (DishType) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
		
    	return "Id: " + this.getId() + " Title: " + this.getTitle() + " Desc: " + this.getDescription();
	}
	
}

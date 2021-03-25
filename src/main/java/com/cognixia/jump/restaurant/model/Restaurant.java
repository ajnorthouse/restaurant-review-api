package com.cognixia.jump.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long restaurantId;
	
	@NotNull(message = "Restaurant must have a name")
	@Column(unique = true)
	String name;
	
	@NotNull(message = "Restaurant must have an address")
	@Column(unique = true)
	String address;
	
	@NotNull(message = "Restaurant must have a type of cuisine")
	String cuisine;
	
	@Column(columnDefinition = "integer default 0")
	int averageRating;
	
	public Restaurant() {
		super();
		this.restaurantId = -1L;
		this.name = "N/A";
		this.address = "N/A";
		this.cuisine = "N/A";
		this.averageRating = 0;
	}

	public Restaurant(Long restaurantId, @NotNull(message = "Restaurant must have a name") String name,
			@NotNull(message = "Restaurant must have an address") String address,
			@NotNull(message = "Restaurant must have a type of cuisine") String cuisine, int averageRating) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.cuisine = cuisine;
		this.averageRating = averageRating;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", cuisine="
				+ cuisine + ", averageRating=" + averageRating + "]";
	}
	
}

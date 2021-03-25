package com.cognixia.jump.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long reviewId;
	
	@NotNull(message = "Review must have a rating")
	int rating;
	
	@Column(columnDefinition = "varchar(300) default 'You can eat here if you have no other way to go'")
	String description;
	
	@NotNull(message = "User id cannot be null")
	int userId;
	
	@NotNull(message = "Restaurant id cannot be null")
	int restaurantId;
	
	public Review() {
		super();
		this.reviewId = -1L;
		this.rating = 0;
		this.description = "N/A";
		this.userId = -1;
		this.restaurantId = -1;
	}

	
	public Review(Long reviewId, @NotNull(message = "Review must have a rating") int rating, String description,
			@NotNull(message = "User id cannot be null") int userId,
			@NotNull(message = "Restaurant id cannot be null") int restaurantId) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.description = description;
		this.userId = userId;
		this.restaurantId = restaurantId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}

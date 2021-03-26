package com.cognixia.jump.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long userId;
	
	@NotNull(message = "User must have a username")
	@Column(unique = true)
	String username;
	
	@NotNull(message = "User must have a password")
	String password;
	
	@Column(columnDefinition = "varchar(255) default 'Anonymous'")
	String name;
	
	@Column(columnDefinition = "integer default 0")
	int reviewCount;
	
	final String role = "USER";

	public User() {
		super();
		this.userId = -1L;
		this.username = "N/A";
		this.password = "N/A";
		this.name = "Anonymous";
		this.reviewCount = 0;
	}

	public User(Long userId, @NotNull(message = "User must have a username") String username,
			@NotNull(message = "User must have a password") String password, String name, int reviewCount) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.reviewCount = reviewCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", name=" + name
				+ ", reviewCount=" + reviewCount + "]";
	}
	
}

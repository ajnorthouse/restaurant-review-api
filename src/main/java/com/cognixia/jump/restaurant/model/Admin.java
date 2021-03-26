package com.cognixia.jump.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long adminId;
	
	@NotNull(message = "Admin must have a username")
	@Column(unique = true)
	String username;
	
	@NotNull(message = "Admin must have a password")
	String password;
	
	@Column(columnDefinition = "varchar(255) default 'Captain'")
	String name;
	
	final String role = "ADMIN";

	public Admin() {
		this.adminId = -1L;
		this.username = "N/A";
		this.password = "N/A";
		this.name = "Captain";
	}
	
	public Admin(Long userId, @NotNull(message = "Admin must have a username") String username,
			@NotNull(message = "Admin must have a password") String password, String name) {
		super();
		this.adminId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public String getRole() {
		return role;
	}
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", username=" + username + ", name=" + name
				+ "]";
	}
	
	
}

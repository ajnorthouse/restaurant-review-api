package com.cognixia.jump.restaurant.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.Admin;
import com.cognixia.jump.restaurant.model.User;

@Service
public class ResUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private List<GrantedAuthority> authorities;

	public ResUserDetails() {
		
	}
	
	public ResUserDetails(String username) {
		this.username = username;
	}
	
	public ResUserDetails(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
	}
	
	public ResUserDetails(Admin admin) {
		admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(admin.getRole()));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}

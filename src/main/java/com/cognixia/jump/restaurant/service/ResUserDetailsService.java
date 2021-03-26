package com.cognixia.jump.restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.User;
import com.cognixia.jump.restaurant.repo.UserRepository;

@Service
public class ResUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findByUsername(username);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException(username + " doesn't exists");
		}
		return null;
	}

}

package com.cognixia.jump.restaurant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.Admin;
import com.cognixia.jump.restaurant.model.User;
import com.cognixia.jump.restaurant.repo.AdminRepository;
import com.cognixia.jump.restaurant.repo.UserRepository;

@Service
public class ResUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository urepo;
	
	@Autowired
	AdminRepository arepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = urepo.findByUsername(username);
		Optional<Admin> admin = arepo.findByUsername(username);
		
		if(user.isPresent()) {
			
			return new ResUserDetails(user.get());
			
		} else if (admin.isPresent()) {
			
			return new ResUserDetails(admin.get());
			
		} else {
			
			throw new UsernameNotFoundException(username + " doesn't exists");
			
		}
	}

}

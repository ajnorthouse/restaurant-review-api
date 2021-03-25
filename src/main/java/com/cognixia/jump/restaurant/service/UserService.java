package com.cognixia.jump.restaurant.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.User;
import com.cognixia.jump.restaurant.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	//Get All Users
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	//Find By ID
	public User getUserById(long id) {
		
		Optional<User> user = repo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return new User();
		
	}
	
	//Find By username
	public User getUserByUserName(String username) {
		
		Optional<User> user = repo.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		}
		return new User();
		
	}
	
	//Create user
	public User createUser(@Valid User user) {
		
		if(repo.existsById(user.getUserId())) {
			return new User();
		}
		return repo.save(user);
		
	}
	
	//Update user
	public User updateUser(@Valid User user) {
		
		if(repo.existsById(user.getUserId())){
			return repo.save(user);
		}
		return new User();
		
	}
	
	//Delete user
	public boolean deleteUser(long id) {
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
		
	}
}

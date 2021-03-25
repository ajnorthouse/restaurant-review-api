package com.cognixia.jump.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.restaurant.model.User;
import com.cognixia.jump.restaurant.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	//Get All Users
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	//Find By ID
	@GetMapping("/user/id/{id}")
	public User getUserById(@PathVariable long id) {
		return service.getUserById(id);
	}
	
	//Find By username
	@GetMapping("/user/username/{username}")
	public User getUserByUserName(@PathVariable String username) {
		return service.getUserByUserName(username);
	}
	
	//Create user
	@PostMapping("/user/add")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User u = service.createUser(user);
		if(u.getUserId() == -1L)
		{
			return new ResponseEntity<>(u, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(u, HttpStatus.CREATED);
		
	}
	
	//Update user
	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		User u = service.updateUser(user);
		if(u.getUserId() == -1L)
		{
			return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(u, HttpStatus.OK);
		
	}
	
	//Delete user
	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		
		boolean update = service.deleteUser(id);
		if(!update) {
			return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
}

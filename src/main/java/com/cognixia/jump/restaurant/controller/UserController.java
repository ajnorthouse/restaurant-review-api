package com.cognixia.jump.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//This is from David's security implementation, uncomment it to try it out!
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	//Get All Users *For testing only comment out when deploy*
	@GetMapping("/user")
    @CrossOrigin(origins = "http://localhost:3000")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	//Find By ID *For testing only comment out when deploy*
	@GetMapping("/user/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public User getUserById(@PathVariable("id") long id) {
		return service.getUserById(id);
	}
	
	//Find By username
	@GetMapping("/user/username/{username}")
    @CrossOrigin(origins = "http://localhost:3000")
	//This is from David's security implementation, uncomment it to try it out!
//	@PreAuthorize("#username == authentication.name")
	public User getUserByUserName(@PathVariable("username") String username) {
		return service.getUserByUserName(username);
	}
	
	//Create user
	@PostMapping("/user/add")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<User> createUser(@RequestBody() User user) {
		
		User u = service.createUser(user);
		if(u.getUserId() == -1L)
		{
			return new ResponseEntity<>(u, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(u, HttpStatus.CREATED);
		
	}
	
	//Update user
	@PutMapping("/user/update")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<User> updateUser(@RequestBody  @Param("user") User user) {
		
		User u = service.updateUser(user);
		if(u.getUserId() == -1L)
		{
			return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(u, HttpStatus.OK);
		
	}
	
	//Delete user
	@DeleteMapping("user/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		
		boolean update = service.deleteUser(id);
		if(!update) {
			return new ResponseEntity<>("User was not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
}

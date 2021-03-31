package com.cognixia.jump.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.restaurant.model.Restaurant;
import com.cognixia.jump.restaurant.service.RestaurantService;

@RequestMapping("/api")
@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	//GET ALL RESTAURANTS
	@GetMapping("/restaurant")
    @CrossOrigin(origins = "http://localhost:3000")
	public List<Restaurant> getAllRestaurants() {
		return service.getAllRestaurants();
	}
	
	//FIND BY ID
	@GetMapping("/restaurant/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public Restaurant getRestaurantById(@PathVariable long id) {
		return service.getRestaurantById(id);
	}
	
	//FIND BY NAME
	@GetMapping("/restaurant/name/{name}")
    @CrossOrigin(origins = "http://localhost:3000")
	public Restaurant getRestaurantByName(@PathVariable String name) {
		return service.getRestaurantByName(name);
	}
	
	//CREATE RESTAURANT
	@PostMapping("/restaurant/add")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Restaurant> createRestauant(@RequestBody Restaurant res) {
		
		Restaurant newRes = service.createRestaurant(res);
		if(newRes.getRestaurantId()==-1L)
			return new ResponseEntity<>(newRes, HttpStatus.CONFLICT);
		return new ResponseEntity<>(newRes, HttpStatus.CREATED);	
	}
	
	//UPDATE RESTAURANT
	@PutMapping("/restaurant/update")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant res) {	
		Restaurant toBeUpdated = service.updateRestaurant(res);
		if(toBeUpdated.getRestaurantId()==-1L)
			return new ResponseEntity<>(toBeUpdated, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(toBeUpdated, HttpStatus.OK);	
	}
	
	//DELETE RESTAURANT
	@DeleteMapping("/restaurant/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> deleteRestaurant(@PathVariable long id) {
		
		boolean deleted = service.deleteRestaurant(id); 
		if(!deleted) return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>("Restaurant deleted Successfully", HttpStatus.OK);
		
	}
	
	//FUZZY SEARCH
	@GetMapping("/restaurant/fuzzysearch/{searchString}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Restaurant> fuzzySearch(@PathVariable String searchString) {
	
		return service.fuzzySearch(searchString);
	}
	
}

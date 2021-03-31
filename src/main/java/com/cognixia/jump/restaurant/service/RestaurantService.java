package com.cognixia.jump.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.Restaurant;
import com.cognixia.jump.restaurant.repo.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository repo;
	
	//GET ALL RESTAURANTS
	public List<Restaurant> getAllRestaurants() {	
		return repo.findAll();		
	}
	
	//FIND BY ID
	public Restaurant getRestaurantById(long id) {
		
		Optional<Restaurant> found = repo.findById(id);
		
		if(found.isPresent()) return found.get();
		
		return new Restaurant();
		
	}
	
	//FIND BY NAME
	public Restaurant getRestaurantByName(String name) {
		
		Optional<Restaurant> found = repo.findByName(name);
		
		if(found.isPresent()) return found.get();
		
		return new Restaurant();
		
	}
	
	//CREATE RESTAURANT
	public Restaurant createRestaurant(@Valid Restaurant res) {
		
		if(repo.existsById(res.getRestaurantId())) return repo.save(res);
		
		return new Restaurant();
		
	}
	
	//UPDATE RESTAURANT
	public Restaurant updateRestaurant(@Valid Restaurant res) {
		
		if(repo.existsById(res.getRestaurantId())) return repo.save(res);
		
		return new Restaurant();
		
	}
	
	//DELETE RESTAURANT
	public boolean deleteRestaurant(long id) {
		
		if(repo.existsById((id))) {
			repo.deleteById(id);
			return true;
		}
		
		return false;
		
	}
	
	//FUZZY SEARCH
	public List<Restaurant> fuzzySearch(String searchString) {
		List<Restaurant> results = new ArrayList<Restaurant>();
		
			
		List<Restaurant> allRes = repo.findAll();
		
			
		results = allRes.stream()
				.filter(res -> res.getName().equals(searchString))
						.collect(Collectors.toList());
				
		
		return results;
	}
	
}

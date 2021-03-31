package com.cognixia.jump.restaurant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.restaurant.model.Restaurant;



@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	Optional<Restaurant> findByName(String name);
}

package com.cognixia.jump.restaurant.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.restaurant.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	Optional<Review> findById(Long id);
	
}

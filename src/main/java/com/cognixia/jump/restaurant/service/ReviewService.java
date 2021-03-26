package com.cognixia.jump.restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.Review;
import com.cognixia.jump.restaurant.repo.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository repo;
	
	//GET ALL
	public List<Review> getAllReviews() {
		return repo.findAll();
	}
	
	//FIND REVIEW BY ID
	public Review getReviewById(long id) {
		
		Optional<Review> foundReview = repo.findById(id);
		
		if(foundReview.isPresent()) return foundReview.get();
		
		return new Review();
		
	}
	
	
	//FIND REVIEW BY RATING (filtering reviews based on no of stars)
	public List<Review> getReviewsByRating(int rating) {
		
		List<Review> all = repo.findAll();
		
		List<Review> filteredReviews = all.stream()
				.filter(r -> r.getRating() == rating)
				.collect(Collectors.toList());
		
		return filteredReviews;
		
	}
	
	//FIND REVIEW BY USER ID
	public Review getReviewByUserId(long userId) {
		
		Optional<Review> foundReview = repo.findByUserId(userId);
		
		if(foundReview.isPresent()) return foundReview.get();
		
		return new Review(); 
		
	}
	
	//FIND REVIEW BY RESTAURANT ID
	public Review getReviewByRestaurantId(long restaurantId) {
		
		Optional<Review> foundReview = repo.findByRestaurantId(restaurantId);
		
		if(foundReview.isPresent()) return foundReview.get();
		
		return new Review(); 
		
	}
	
	//CREATE REVIEW
	public Review createReview(@Valid Review rev) {
		
		if(repo.existsById(rev.getReviewId())) return new Review();
		
		return repo.save(rev);
		
	}
	
	//UPDATE REVIEW
	public Review updateReview(@Valid Review rev) {
		
		if(repo.existsById(rev.getReviewId())) return repo.save(rev);
		
		return new Review();
		
	}
	
	//DELETE REVIEW
	public boolean deleteReview(long id) {
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
		
	}
	
	
}

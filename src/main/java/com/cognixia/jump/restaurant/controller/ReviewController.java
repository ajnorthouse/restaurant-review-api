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

import com.cognixia.jump.restaurant.model.Review;
import com.cognixia.jump.restaurant.service.ReviewService;

@RequestMapping("/api")
@RestController
public class ReviewController {
	
	@Autowired
	ReviewService service;
	
	//GET ALL REVIEWS
	@GetMapping("/review")
	public List<Review> getAllReviews() {
		return service.getAllReviews();
	}
	
	//FIND REVIEW BY ID
	@GetMapping("/review/id/{id}")
	public Review getReviewById(@PathVariable long id) {
		return service.getReviewById(id);
	}
	
	//FIND REVIEW BY RATING (filtering reviews based on no of stars)
	@GetMapping("/review/rating/{rating}")
	public List<Review> getReviewsByRating(@PathVariable int rating) {
		return service.getReviewsByRating(rating);
	}
	
	//FIND REVIEW BY USER ID
	@GetMapping("/review/userid/{userId}")
	public Review getReviewByUserId(@PathVariable long userId) {
		return service.getReviewByUserId(userId);
	}
	
	//FIND REVIEW BY RESTAURANT ID
	@GetMapping("/review/rrestaurantid/{restaurantId}")
	public Review getReviewByRestaurantId(@PathVariable long restaurantId) {
		return service.getReviewByRestaurantId(restaurantId);
	}
	
	//CREATE REVIEW
	@PostMapping("/review/add")
	public ResponseEntity<Review> createRreview(@RequestBody Review rev) {
		
		Review newRev = service.createReview(rev);
		
		if(newRev.getReviewId()==-1L) {
			return new ResponseEntity<>(newRev, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(newRev, HttpStatus.CREATED);	
	}
	
	//UPDATE REVIEW
	@PutMapping("/review/update")
	public ResponseEntity<Review> updateReview(@RequestBody Review rev) {
		
		Review toBeUpdated = service.updateReview(rev);
		
		if(toBeUpdated.getReviewId()==-1L) {
			return new ResponseEntity<>(toBeUpdated, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toBeUpdated, HttpStatus.OK);		
	}
	
	//DELETE REVIEW
	@DeleteMapping("/review/delete/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable long id) {
		
		boolean deleted = service.deleteReview(id);
		
		if(!deleted) {
			return new ResponseEntity<>("This review does not exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Revview deleted Successfully", HttpStatus.OK);
		
	}
	
}

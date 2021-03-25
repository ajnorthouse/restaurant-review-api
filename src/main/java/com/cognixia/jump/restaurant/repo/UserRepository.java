package com.cognixia.jump.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.restaurant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	

}

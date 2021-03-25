package com.cognixia.jump.restaurant.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.restaurant.model.Admin;
import com.cognixia.jump.restaurant.repo.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository repo;
	
	//Get All Admins
	public List<Admin> getAllAdmins() {
		return repo.findAll();
	}
	
	//Find By ID
	public Admin getAdminById(long id) {
		
		Optional<Admin> admin = repo.findById(id);
		if(admin.isPresent()) {
			return admin.get();
		}
		return new Admin();
		
	}
	
	//Find By username
	public Admin getAdminByUserName(String username) {
		
		Optional<Admin> admin = repo.findByUsername(username);
		if(admin.isPresent()) {
			return admin.get();
		}
		return new Admin();
		
	}
	
	//Create admin
	public Admin createAdmin(@Valid Admin admin) {
		
		if(repo.existsById(admin.getAdminId())) {
			return new Admin();
		}
		return repo.save(admin);
		
	}
	
	//Update admin
	public Admin updateAdmin(@Valid Admin admin) {
		
		if(repo.existsById(admin.getAdminId())){
			return repo.save(admin);
		}
		return new Admin();
		
	}
	
	//Delete admin
	public boolean deleteAdmin(long id) {
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
		
	}	
}

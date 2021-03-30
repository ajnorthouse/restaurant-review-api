package com.cognixia.jump.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.restaurant.model.Admin;
import com.cognixia.jump.restaurant.service.AdminService;

@RequestMapping("/api")
@RestController
public class AdminController {

	@Autowired
	AdminService service;
	
	//Get All admins
	@GetMapping("/admin")
    @CrossOrigin(origins = "http://localhost:3000")
	public List<Admin> getAllAdmins() {
		return service.getAllAdmins();
	}
	
	//Find By ID
	@GetMapping("/admin/id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public Admin getAdminById(@PathVariable long id) {
		return service.getAdminById(id);
	}
	
	//Find By username
	@GetMapping("/admin/username/{username}")
    @CrossOrigin(origins = "http://localhost:3000")
	@PreAuthorize("#username == authentication.name")
	public Admin getAdminByUserName(@PathVariable("username") String username) {
		return service.getAdminByUserName(username);
	}
	
	//Create admin
	@PostMapping("/admin/add")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
		
		Admin a = service.createAdmin(admin);
		if(a.getAdminId() == -1L)
		{
			return new ResponseEntity<>(a, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(a, HttpStatus.CREATED);
		
	}
	
	//Update admin
	@PutMapping("/admin/update")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
		
		Admin a = service.updateAdmin(admin);
		if(a.getAdminId() == -1L)
		{
			return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(a, HttpStatus.OK);
		
	}
	
	//Delete admin
	@DeleteMapping("admin/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> deleteAdmin(@PathVariable long id) {
		
		boolean update = service.deleteAdmin(id);
		if(!update) {
			return new ResponseEntity<>("Admin was not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Admin deleted successfully", HttpStatus.OK);
	}
	
}

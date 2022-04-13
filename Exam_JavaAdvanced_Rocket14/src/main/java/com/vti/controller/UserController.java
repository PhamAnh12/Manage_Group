package com.vti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.vti.dto.UserDTO;
import com.vti.entity.User;
import com.vti.service.UserService;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {
	@Autowired
	private UserService service;
//	@GetMapping()
//	public ResponseEntity<?> getAllUsers() {
//	List <UserDTO>  dtos = service.getAllUsers();
//		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
//
//	}
	@GetMapping()
	public ResponseEntity<?> getUser(@RequestParam String username) {
		UserDTO  entities = service.findByUserName(username);
		return new ResponseEntity<UserDTO>(entities, HttpStatus.OK);

	}
	
	@PostMapping()
	public ResponseEntity<?> createGroup(@RequestBody User user) {
		service.createUser(user);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}
	

	
}

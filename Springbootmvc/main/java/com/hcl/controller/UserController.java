package com.hcl.controller;

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

import com.hcl.dto.UserDTO;
import com.hcl.exceptions.EmailAlreadyExistsException;
import com.hcl.exceptions.ResourceNotFoundException;
import com.hcl.service.UserService;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/users")//this is the default end for the app
public class UserController {
     
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUser(){
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO)
			throws EmailAlreadyExistsException{
		UserDTO savedUser = userService.createUser(userDTO);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) throws ResourceNotFoundException{
		UserDTO user = userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<UserDTO> updateEntity(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO)
			throws ResourceNotFoundException{
		UserDTO updatedUser = userService.updateUser(id,userDTO);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("{id}")
	public String deleteEntity(@PathVariable Long id) throws ResourceNotFoundException{
		String deleteUser = userService.deleteUser(id);
		return deleteUser;
	}
}

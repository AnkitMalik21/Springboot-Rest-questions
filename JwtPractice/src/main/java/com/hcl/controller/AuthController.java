package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.model.User;
import com.hcl.repository.UserRepository;
import com.hcl.service.JwtService;

import lombok.Data;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService; 
    
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
    	System.out.println("AuthRequest " + request);
    	User user = User.builder().username(request.getUsername())
    			.password(passwordEncoder.encode(request.getPassword()))
    			.build();
    	
    	userRepository.save(user);
    	return "User registered successfully";
    }
    
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
    	User user = (User) userRepository.findByUsername(request.getUsername())
    			.orElseThrow(()-> new RuntimeException("Did not found the error"));
    	
    	if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    		return jwtService.generationToken(user);
    	}else{
            throw new RuntimeException("Invalid credential");
    	}
    }
}

@Data
class AuthRequest{
	private String username;
	private String password;
}

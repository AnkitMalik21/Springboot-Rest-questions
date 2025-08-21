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
     private JwtService jwtService;
     
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

  
	
	@PostMapping("/register")
	public String register(@RequestBody AuthRequest request) {
	     System.out.println("AuthRequest " + request);
	     User user = User.builder().username(request.getUsername())
	    		 .password(passwordEncoder.encode(request.getUsername()))
	    		 .build();
	    		    		
	      userRepository.save(user);	    
	      return "User register successfully";
	}
	
	@PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(()-> new RuntimeException("Did not found the error"));
		if(passwordEncoder.matches(request.getPassword(),user.getPassword())) {
			return jwtService.generateToken(user);
		}else {
			throw new RuntimeException("Invalid credentails");
		}
	}
}

@Data
class AuthRequest{
	private String username;
	private String password;
}

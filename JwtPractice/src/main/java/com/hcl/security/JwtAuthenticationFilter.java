package com.hcl.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hcl.model.User;
import com.hcl.repository.UserRepository;
import com.hcl.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			username = jwtService.extractUsername(jwtToken);
			System.out.println("User name: " + username);
		}
		
		System.out.println("User name: " + userRepository.findByUsername(username));
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			User user = userRepository.findByUsername(username).orElse(null);
			
			System.out.println("jwtService.validationToken(jwtToken,user): " + jwtService.validationToken(jwtToken, user));
			
			if(user != null && jwtService.validationToken(jwtToken, user)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						user,
						null,
						null
				);
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		System.out.println("Username from token: " + username);
	    System.out.println("Authenticated? " + (SecurityContextHolder.getContext().getAuthentication()!=null));
	    System.out.println("Jwt token: " + jwtToken);
	    System.out.println("SecurityContext: " + SecurityContextHolder.getContext().getAuthentication());
	    
	    filterChain.doFilter(request,response);
		
	}    
}

package com.hcl.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {
     @Value("${jwt.secret}")
     private String secret;
     
     @Value("${jwt.expiration}")
     private long expiration;
     
     private Key getSignKey() {
    	 return Keys.hmacShaKeyFor(secret.getBytes());
     }
     
     public String generateToken(User user) {
    	 return Jwts.builder()
    			 .setSubject(user.getUsername())
    			 .setIssuedAt(new Date())
    			 .setExpiration(new Date(System.currentTimeMillis() +  expiration))
    			 .signWith(getSignKey(),SignatureAlgorithm.HS256)
    			 .compact();
    			 
     }
     
     public String extractUsername(String token) {
    	 return Jwts.parserBuilder()
    			 .setSigningKey(getSignKey())
    			 .build()
    			 .parseClaimsJws(token)
    			 .getBody()
    			 .getSubject();		 
     }
     
     public boolean validateToken(String token,User user) {
    	 System.out.println("validating token for user: " + token + " : " + user);
    	 String username = extractUsername(token);
    	 System.out.println("validation token for user : " + user.getUsername());
    	 return username.equals(user.getUsername()) && !isTokenExpired(token);
     }
     
     private boolean isTokenExpired(String token) {
    	 Date expiration =Jwts.parserBuilder()
    			 .setSigningKey(getSignKey())
    			 .build()
    			 .parseClaimsJws(token)
    			 .getBody()
    			 .getExpiration();
    	 return expiration.before(new Date());
     }
}

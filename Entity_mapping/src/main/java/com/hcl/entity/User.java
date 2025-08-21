package com.hcl.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     private String username;
     
     @OneToOne(cascade = CascadeType.ALL)//operation done on parent class is reflected on class child like update, save delete
     @JoinColumn(name ="profile_id",referencedColumnName = "id")//it is the foreign that is used to join to entity
     
     private UserProfile profile;
     
     //Constructor ,getter,setters
     public User() {
     }

	 public User(Long id, String username, UserProfile profile) {
		super();
		this.id = id;
		this.username = username;
		this.profile = profile;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getUsername() {
		 return username;
	 }

	 public void setUsername(String username) {
		 this.username = username;
	 }

	 public UserProfile getProfile() {
		 return profile;
	 }

	 public void setProfile(UserProfile profile) {
		 this.profile = profile;
	 }
     
     
     
     
}

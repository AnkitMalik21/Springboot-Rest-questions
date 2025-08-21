package com.hcl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserProfile {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String address;
     private String phoneNumber;
      
     @OneToOne(mappedBy = "profile")
     
     private User user;
     
     public UserProfile() {}
	 public UserProfile(Long id, String address, String phoneNumber, User user) {
		super();
		this.id = id;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.user = user;
	 }
	 public Long getId() {
		 return id;
	 }
	 public void setId(Long id) {
		 this.id = id;
	 }
	 public String getAddress() {
		 return address;
	 }
	 public void setAddress(String address) {
		 this.address = address;
	 }
	 public String getPhoneNumber() {
		 return phoneNumber;
	 }
	 public void setPhoneNumber(String phoneNumber) {
		 this.phoneNumber = phoneNumber;
	 }
	 public User getUser() {
		 return user;
	 }
	 public void setUser(User user) {
		 this.user = user;
	 }
	 
	 //getter setter
	 
	 
     
     
}

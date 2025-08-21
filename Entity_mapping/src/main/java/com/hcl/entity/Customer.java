package com.hcl.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customer {
     @Id
     @GeneratedValue
     private Long id;
     
     private String name;
     
     //order entity owns the relationship because it hold the foreign key column
     @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
     private List<Order> orders;
     
     //Constructor ,getter and setter
     public Customer() {}

	 public Customer(Long id, String name, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public List<Order> getOrders() {
		 return orders;
	 }

	 public void setOrders(List<Order> orders) {
		 this.orders = orders;
	 }
     
     
}

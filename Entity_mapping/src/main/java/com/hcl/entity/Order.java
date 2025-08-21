package com.hcl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders") //order is reversed keyword in sql
public class Order {
     @Id
     @GeneratedValue
     private Long id;
     
     private String product;
     
     @ManyToOne
     @JoinColumn(name = "customer_id")//name of foreign key column
     private Customer customer;
     
     //constructor
     public Order() {}

	 public Order(Long id, String product, Customer customer) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
	 }
     
     
     
}

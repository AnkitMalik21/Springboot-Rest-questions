package com.hcl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// we dont need Junit 5 as we have spring start test file
@Entity
@Table(name="Product")
public class Product {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      
      private String name;
      private double price;
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
	  public double getPrice() {
		  return price;
	  }
	  public void setPrice(double price) {
		  this.price = price;
	  }      
}

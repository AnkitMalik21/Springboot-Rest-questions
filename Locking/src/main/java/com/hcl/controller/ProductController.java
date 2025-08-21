package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
     
	@Autowired
	private ProductService productService;
	
	@PutMapping("/optimistic/{id}")
	public String optimisticUpdate(@PathVariable Long id,@RequestParam int qty) {
	     try {
			productService.updateWithOptimisticLock(id, qty);
			return "updated with optimistic locking.";
		} catch (Exception e) {
			return "Optimistic lock error: " + e.getMessage();
		}	
	}
	
	@PutMapping("/pessimistic/{id}")
	public String pessimisticUpdate(@PathVariable Long id,@RequestParam int qty) {
		try {
			productService.updateWithOptimisticLock(id, qty);
			return "Updated with pessimistic locking.";
		} catch (Exception e) {
			return "Pessimistic lock error: " + e.getMessage(); 
		}
	}
}

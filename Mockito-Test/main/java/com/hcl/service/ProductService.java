package com.hcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.Product;
import com.hcl.repository.ProductRepository;

@Service
public class ProductService {
	/*
	 saveProduct
	 getAllProduct
	 getProductById
	 deleteProduct
	 getProductByName
	 */
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Did not found product with id: " + id));
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> getProductByName(String name){
		return productRepository.findByName(name);
	}
}

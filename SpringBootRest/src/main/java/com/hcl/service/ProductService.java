package com.hcl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.Product;
import com.hcl.repository.ProductRepository;

@Service
public class ProductService {
     
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProduct(){
		return  productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	
	public List<Product> getProductByName(String name){
		return productRepository.findByName(name);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}

package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.Product;
import com.hcl.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public void updateWithOptimisticLock(Long id,int newQty) {
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Product not found"));
		
		product.setQuantity(newQty);
		productRepository.save(product);
	}
	
	@Transactional
	public void updateWithPessimisticLock(Long id,int newQty) {
		Product product = productRepository.findByIdWithPessimisticLock(id);
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		product.setQuantity(newQty);
	}
}

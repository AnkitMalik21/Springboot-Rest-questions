package com.hcl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.entity.Product;
import com.hcl.service.ProductService;

@RestController //this is the combination of @Controller + @ResponseBody
@RequestMapping("/products")
public class ProductController {
	/*
	 * saveProduct getAllProduct getProductById updateProduct deleteProduct
	 */
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product saveProduct = productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = productService.getAllProduct();
		return ResponseEntity.ok(products);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){
		Optional<Product> product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
		Optional<Product> existingProduct = productService.getProductById(id);
		if(existingProduct.isPresent()) {
		     product.setId(id);
		     Product updatedProduct = productService.saveProduct(product);
		     return ResponseEntity.ok(updatedProduct);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMapping(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
}

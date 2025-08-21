package com.hcl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.entity.Product;

import com.hcl.service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {

 
	@Autowired
	private ProductService productService;
	
	/*
	 saveProduct
	 getAllProduct
	 getProductById
	 deleteProduct
	 getProductByName
	 */
	
	@PostMapping("")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product product1 = productService.saveProduct(product);
		return ResponseEntity.ok(product1);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> listProduct= productService.getAllProduct();
		return ResponseEntity.ok(listProduct);
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
		List<Product> product = productService.getProductByName(name);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
}

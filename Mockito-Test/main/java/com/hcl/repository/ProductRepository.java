package com.hcl.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
     @Query("SELECT p FROM Product p WHERE p.name = :name")
     List<Product>findByName(@Param("name")String name);
}

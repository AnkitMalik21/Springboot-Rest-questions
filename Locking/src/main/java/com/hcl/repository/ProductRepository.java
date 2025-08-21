package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Product;

import jakarta.persistence.LockModeType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @Query("SELECT p FROM Product p WHERE p.id =:id")
     Product findByIdWithPessimisticLock(@Param("id")Long id);
}

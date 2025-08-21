package com.hcl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
     @Query("Select c from Customer c where c.customerName = :name")
     Optional<Customer> findByName(@Param("name") String name);
}

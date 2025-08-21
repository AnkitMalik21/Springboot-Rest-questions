package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long>{

}

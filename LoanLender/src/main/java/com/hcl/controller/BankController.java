package com.hcl.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.LoanLenderApplication;
import com.hcl.entity.Customer;
import com.hcl.entity.Loan;
import com.hcl.service.BankService;


@RestController
@RequestMapping("/banking")
public class BankController {

    private final LoanLenderApplication loanLenderApplication;
    @Autowired
    private BankService bankService;

    BankController(LoanLenderApplication loanLenderApplication) {
        this.loanLenderApplication = loanLenderApplication;
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
    	String addedCustomer = bankService.saveCustomer(customer);
    	return ResponseEntity.ok(addedCustomer);
    }
    
    @PostMapping("allLoan/{id}/loan")
    public String addLoan(@PathVariable Long id,@RequestBody List<Loan>loan){
    	bankService.allocateLoanToCustomer(id,loan);
    	return "loan added successfully";
    }
    
    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
    	return bankService.getCustomerById(id);
    }
    
    @GetMapping("/getLoan/{id}")
    public List<Loan> getLoansById(@PathVariable Long id){
    	Customer customer = getCustomerById(id);
    	List<Loan> loan =customer.getLoanList();
    	return loan;
    }
    
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Optional<Customer>> getLoanByName(@PathVariable String name){
    	Optional<Customer> customer = bankService.getCustomerByName(name);
    	return ResponseEntity.ok(customer);
    }
    
}

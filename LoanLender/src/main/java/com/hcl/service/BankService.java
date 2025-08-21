package com.hcl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.Customer;
import com.hcl.entity.Loan;
import com.hcl.repository.CustomerRepository;
import com.hcl.repository.LoanRepository;

@Service
public class BankService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoanRepository loanRepository;

	public String saveCustomer(Customer cusomter) {
		customerRepository.save(cusomter);
		return "Customer added successfull";
	}

	public void allocateLoanToCustomer(long customerId, List<Loan> loans) {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);

		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			for (Loan loan : loans) {
				loan.setCustomer(customer);
			}
			loanRepository.saveAll(loans);
		} else {
			throw new RuntimeException("Customer not found with id: " + customerId);
		}
	}

	public Customer getCustomerById(Long customerId) {
    	 return customerRepository.findById(customerId).
    			 orElseThrow(()-> new RuntimeException("Customer not found with Id: " + customerId));
    	 
     }

	public List<Loan> getLoanOfCustomer(Long customerId) {
		Customer customer = getCustomerById(customerId);
		List<Loan> loan = customer.getLoanList();
		return loan;
	}
	
	public Optional<Customer> getCustomerByName(String name) {
		return customerRepository.findByName(name);
	}

}

/*
 * Step-by-Step Breakdown Step 1: Find the Customer java Optional<Customer>
 * customerOptional = customerRepository.findById(customerId); What happens:
 * 
 * The method searches for a customer in the database using their ID
 * 
 * Returns an Optional<Customer> (which is like a box that might or might not
 * contain a customer)
 * 
 * If customer exists → box contains the customer
 * 
 * If customer doesn't exist → box is empty
 * 
 * Analogy: Like looking for a specific file folder in a filing cabinet using a
 * folder number.
 * 
 * Step 2: Check if Customer Exists java if (customerOptional.isPresent()) {
 * What happens:
 * 
 * Checks if the "box" actually contains a customer
 * 
 * isPresent() returns true if customer was found, false if not
 * 
 * Step 3: Get the Customer Object java Customer customer =
 * customerOptional.get(); What happens:
 * 
 * Extracts the actual Customer object from the Optional "box"
 * 
 * Now you have the real customer object to work with
 * 
 * Step 4: Link Each Loan to the Customer java for (Loan loan : loans) {
 * loan.setCustomer(customer); } What happens:
 * 
 * Loops through every loan in the provided list
 * 
 * For each loan, sets its customer field to point to the found customer
 * 
 * This establishes the relationship in Java objects (before saving to database)
 * 
 * Analogy: Like writing the customer's name on each loan document so you know
 * who it belongs to.
 * 
 * Step 5: Save All Loans to Database java loanRepository.saveAll(loans); What
 * happens: (See detailed explanation below)
 * 
 * Step 6: Handle Missing Customer java } else { throw new
 * RuntimeException("Customer not found with id: " + customerId); } What
 * happens:
 * 
 * If customer wasn't found, throws an error with a descriptive message
 * 
 * Prevents the method from continuing with invalid data
 * 
 * What Does saveAll(loans) Do in Detail? Purpose saveAll() is a Spring Data JPA
 * method that saves multiple entities to the database in one operation.
 * 
 * How It Works Batch Operation:
 * 
 * Instead of making separate database calls for each loan
 * 
 * Groups all loans together and saves them in one efficient operation
 * 
 * Much faster than calling save() individually for each loan
 * 
 * Insert or Update Logic:
 * 
 * For each loan in the list:
 * 
 * If loan has no ID or is new: Creates a new record in the database
 * 
 * If loan already exists: Updates the existing record with new data
 * 
 * Foreign Key Relationship:
 * 
 * Since we set loan.setCustomer(customer) for each loan
 * 
 * The database will store the correct customerid foreign key
 * 
 * This creates the link between loan and customer in the database
 */
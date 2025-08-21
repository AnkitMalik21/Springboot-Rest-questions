package com.hcl.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	private String customerName;
	private long customerContactNumber;

	/*
	 * mappedBy = "customer" JPA needs to know which field in the "many" side
	 * (Loan) points back to the "one" side (Customer).
	 * 
	 * Easy analogy: Each loan has a field called customer that stores which
	 * customer it belongs to.
	 * 
	 * In code: In your Loan class, you have a field like private Customer
	 * customer;
	 */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Loan> loanList = new ArrayList<>();

	/*
	 * @OneToMany tells JPA there is one-to-many relationship.
	 * mappedBy = "customer" means
	 * "look in the Loan class for the field called customer to connect the two."	
	 * cascade = CascadeType.ALL means
	 * "do any database operation (save, delete, update) on the customer and it will
	 *  automatically do the same for all the orders belonging to that customer."
	 */
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(long customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public List<Loan> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<Loan> loanList) {
		this.loanList = loanList;
	}

}

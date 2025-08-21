package com.hcl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loanNumber;
	private String loanType;
	private double loanAmount;
	private int repayment;
	private double monthlyDue;

	@ManyToOne
	@JoinColumn(name = "customerid", referencedColumnName = "customerId")
	@JsonIgnore
	private Customer customer;

	public long getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(long loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getRepayment() {
		return repayment;
	}

	public void setRepayment(int repayment) {
		this.repayment = repayment;
	}

	public double getMonthlyDue() {
		return monthlyDue;
	}

	public void setMonthlyDue(double monthlyDue) {
		this.monthlyDue = monthlyDue;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
	
}

/*
 * 1. @ManyToOne What it means: Many loans can belong to ONE customer.
 * 
 * Example/Analogy: Imagine you are a bank customer and you have taken multiple
 * loans (car loan, home loan, personal loan, etc.).
 * 
 * Each of your loans relates to YOU as the customer.
 * 
 * If we look from the Loan's side: Many loans → ONE customer.
 * 
 * 2. @JoinColumn(name = "customerid", referencedColumnName = "customerId") What
 * it does: It links the Loan to a specific Customer in the database using a
 * foreign key.
 * 
 * name = "customerid": In the Loan table, there will be a column called
 * customerid.
 * 
 * referencedColumnName = "customerId": In the Customer table, there is a column
 * called customerId, which is the primary key for customers.
 * 
 * How it works: Whenever you create a new Loan, you also set which Customer it
 * belongs to. The Loan record in the database stores the customerid, so it
 * always knows who owns that loan.
 */

/*
 * 3. @JsonIgnore What it means: This tells Jackson—the library used to convert
 * Java objects to JSON (often for APIs)—to not include the customer field when
 * converting Loan to JSON.
 * 
 * Why do this?
 * 
 * To avoid circular references/infinitely large JSON (since Customer has a list
 * of loans and each Loan has a customer), or
 * 
 * To avoid exposing customer data unnecessarily when sending Loan info over the
 * web.
 * 
 * 4. private Customer customer; What it means: Each loan object in Java has a
 * field called customer that points to the Customer who took that loan.
 */
package com.hcl.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;

@Component
public class CabRentBean {
     private String cabType;
     private double rentPerHr;
     
     @Min(value = 1,message="Duration must be 1 or more")
     private int durationInHrs;

	 public String getCabType() {
		 return cabType;
	 }

	 public void setCabType(String cabType) {
		 this.cabType = cabType;
	 }

	 public double getRentPerHr() {
		 return rentPerHr;
	 }

	 public void setRentPerHr(double rentPerHr) {
		 this.rentPerHr = rentPerHr;
	 }

	 public int getDurationInHrs() {
		 return durationInHrs;
	 }

	 public void setDurationInHrs(int durationInHrs) {
		 this.durationInHrs = durationInHrs;
	 }
}

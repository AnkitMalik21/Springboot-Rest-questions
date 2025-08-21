package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.model.CabRentBean;

public class CabRentService {
	// calculate the cabRent and return the rent amount
	@Autowired
	private CabRentBean cabBean;
	
	public double calculateCabRent(CabRentBean cabBean) {
		double cabRent = 0.0;//variable
		
		switch (cabBean.getCabType()){
		
		case "Micro-AC": {
			cabBean.setRentPerHr(700);
			break;
		}
		case "Mini-NonAC": {
			cabBean.setRentPerHr(800);
			break;
		}
		case "Mini-AC": {
			cabBean.setRentPerHr(1000);
			break;
		}
		case "Sedan-AC": {
			cabBean.setRentPerHr(1500);
			break;
		}
		case "Luxury": {
			cabBean.setRentPerHr(500);
			break;
		}
		default:
			System.out.println("EXITED");
		}
	
		cabRent = cabBean.getRentPerHr() * cabBean.getDurationInHrs();
		return cabRent;
	}

}
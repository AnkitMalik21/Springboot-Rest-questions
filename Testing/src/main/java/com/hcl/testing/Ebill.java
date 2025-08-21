package com.hcl.testing;

public class Ebill {
     private int unit;
     
     public Ebill(int unit) {
    	 this.unit = unit;
     }
     
     public double Calculate() {
    	 double charge = 0;
    	 
    	 if(unit<=50) {
    		 charge = unit * 2.60;
    	 }else if(unit<=100) {
    		 charge = 50*2.60 + (unit-50)*3.25;
    	 }else if(unit<=200) {
    		 charge = 50*2.60 + 50*3.25 + (unit-100)*5.26;
    	 }else if(unit<=1000) {
    		 charge = 50*2.60 + 50*3.25 + 100*5.26 + (unit-200)*7.75;
    	 }else {
    		 charge = 50*2.60 + 50*3.25 + 100*5.26 + 800*7.75  + (unit-1000)*10; 
    	 }
    	 
    	 return charge;
     }
}

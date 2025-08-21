package com.hcl.testing;

public class Calculation {
    public static int factorial(int n) {
    	int ans=1;
    	for(int i=2;i<=n;i++) {
    		ans *=i;
    	}
    	return ans;
    }
    
    public static int findMax(int arr[]) {
    	int max = Integer.MIN_VALUE;
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i]>max) {
    			max=arr[i];
    		}
    	}
    	
    	return max;
    }
    
    public static int cube(int n) {
    	return n*n*n;
    }
}

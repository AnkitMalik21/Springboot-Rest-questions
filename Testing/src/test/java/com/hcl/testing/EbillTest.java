package com.hcl.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EbillTest {
    Ebill bill = null;
    
    @Test
    public void testInput1() {
    	bill = new Ebill(0);
    	assertEquals(0, bill.Calculate());
    }
    
    @Test
    public void testInput2() {
    	bill = new Ebill(50);
    	assertEquals(130, bill.Calculate());
    }
    
    @Test
    public void testInput3() {
    	bill = new Ebill(100);
    	assertEquals(292.5, bill.Calculate());
    }
    
    @Test
    public void testInput4() {
    	bill = new Ebill(200);
    	assertEquals(818.5, bill.Calculate());
    }
    
    @Test
    public void testInput5() {
    	bill = new Ebill(1000);
    	assertEquals(7018.5, bill.Calculate());
    }
    
    @Test
    public void testInput6() {
    	bill = new Ebill(5000);
    	assertEquals(47018.5, bill.Calculate());
    }
}

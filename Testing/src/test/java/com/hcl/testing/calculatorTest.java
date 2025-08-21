package com.hcl.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class calculatorTest {
     @BeforeAll
     public static void setUpBeforeClass() throws Exception{
    	 System.out.println("before class");
     }
     
     @BeforeEach
     public  void setUp() throws Exception{
    	 System.out.println("Method starting");
     }
     
     @Test
     public void testFindMax() {
    	 System.out.println(10);
    	 assertEquals(10,Calculation.findMax(new int[] {-9,2,3,10}));
     }
     
     @Test
     public void testCube() {
    	 System.err.println(64);
    	 assertEquals(64,Calculation.cube(4));
     }
     
     @Test
     public void testFactorial() {
    	 System.out.println(120);
    	 assertEquals(120, Calculation.factorial(5));
     }
     
     @AfterEach
     public void setDownMethod() {
    	 System.out.println("Ending method");
     }
     
     @AfterAll
     public static void endAll() {
    	 System.out.println("Every Method has ended");
     }
}

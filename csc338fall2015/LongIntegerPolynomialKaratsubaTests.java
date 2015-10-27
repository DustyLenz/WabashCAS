/*
	* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import csc338fall2015.CSC338Utils;
import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.math.*;
import java.util.*;
import static org.junit.Assert.*;

/**
 *
 * @author DustyLenz
 */
public class LongIntegerPolynomialKaratsubaTests {
   public LongIntegerPolynomialKaratsubaTests(){

   }
   @BeforeClass
   public static void setUpClass(){
   }
   @AfterClass
   public static void tearDownClass(){

   }
   @Before
   public void setUp(){

   }
   @After
   public void tearDown(){

   }
   
   @Test
    public void testPolynomialKaratsubaMultiplyByZero(){
        System.out.println("Test: Karatsuba Multiply by Zero");
        ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList.add(LongInteger.ZERO);
			}
			else{
				polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
        
        LongIntegerPolynomial result = polyA.karatsuba(LongIntegerPolynomial.ZERO);
        LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
	@Test
    public void testPolynomialKaratsubaMultiplyByOne(){
        System.out.println("Test: Multiply by One");
        ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList.add(LongInteger.ZERO);
			}
			else{
				polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
        LongIntegerPolynomial expectedResult = polyA;
        
        LongIntegerPolynomial result = polyA.karatsuba(LongIntegerPolynomial.ONE);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testPolynomialKaratsubaMultiply(){
        System.out.println("Test: Multiply");
        ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList.add(LongInteger.ZERO);
			}
			else{
				polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
		
		ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList1.add(LongInteger.ZERO);
			}
			else{
				polyList1.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyB = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = polyA.multiply(polyB);
        
        LongIntegerPolynomial result = polyA.karatsuba(polyB);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testKaratsubaMultiplyCommutativeMultiply(){
        System.out.println("Test: Commutative Multiply");
       ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList.add(LongInteger.ZERO);
			}
			else{
				polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
		
		ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList1.add(LongInteger.ZERO);
			}
			else{
				polyList1.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyB = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = polyA.karatsuba(polyB);
        
        LongIntegerPolynomial result = polyB.karatsuba(polyA);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
   
}

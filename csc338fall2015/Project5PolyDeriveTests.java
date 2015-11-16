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
public class Project5PolyDeriveTests {
   public Project5PolyDeriveTests(){

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
   public void testDerivativeOfZero(){
	   System.out.println("Test: Derivative of 0");
	   LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;
	   LongIntegerPolynomial result = polyA.derivative();
	   boolean testPassed = result.equals(LongIntegerPolynomial.ZERO);
	   assertEquals(true, testPassed);
   }
   @Test
   public void testDerivativeOfOne(){
	   System.out.println("Test: Derivative of 1");
	   LongIntegerPolynomial polyA = LongIntegerPolynomial.ONE;
	   LongIntegerPolynomial result = polyA.derivative();
	   boolean testPassed = result.equals(LongIntegerPolynomial.ZERO);
	   assertEquals(true, testPassed);
   }
   @Test 
   public void testDerivativesNegCoeff(){
      System.out.println("Test: Derivative of Function with negative coefficients");
	  ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
      for(int i = 0; i < 15; i++){
         if(i == 3 || i == 7){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp.negate());
         }
      }
	  ArrayList<LongInteger> intListCopy = new ArrayList<LongInteger>(intList);
	  LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  LongIntegerPolynomial result = polyA.derivative();
	  //System.out.println("PolyA: " + polyA);
	  //System.out.println("result: " + result);
	  ArrayList<LongInteger> checkList = new ArrayList<LongInteger>(result.coefficients());
	  //System.out.println("Checklist size: " + checkList.size());
	  //System.out.println("IntList size: " + intListCopy.size());
	  boolean allPassed = true;
	  for(int i = 1; i < intListCopy.size(); i++){
	  //System.out.println("i: " + i);
		  LongInteger temp = intListCopy.get(i).multiply(LongInteger.valueOf(i));
		  if(!temp.equals(checkList.get(i-1))){
			  allPassed = false;
			  break;
		  }
	  }
	  assertEquals(true, allPassed);
   }
   @Test
   public void testDerivativePosCoeff(){
      System.out.println("Test: Derivative of Function with positive coefficients");
	  ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
      for(int i = 0; i < 15; i++){
         if(i == 3 || i == 7){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp);
         }
      }
	  ArrayList<LongInteger> intListCopy = new ArrayList<LongInteger>(intList);
	  LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  LongIntegerPolynomial result = polyA.derivative();
	  
	  ArrayList<LongInteger> checkList = new ArrayList<LongInteger>(result.coefficients());
	  boolean allPassed = true;
	  for(int i = 1; i < intListCopy.size(); i++){
		  LongInteger temp = intListCopy.get(i).multiply(LongInteger.valueOf(i));
		  if(!temp.equals(checkList.get(i-1))){
			  allPassed = false;
			  break;
		  }
	  }
	  assertEquals(true, allPassed);
   }
}
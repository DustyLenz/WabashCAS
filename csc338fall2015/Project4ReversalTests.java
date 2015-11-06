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
public class Project4ReversalTests {
   public Project4ReversalTests(){

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
   public void testZeroPolyReversal(){
      System.out.println("Test 1: Reversal with a zero polynomial");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;
      int k = (int) (Math.random()*10)+1;
      LongIntegerPolynomial result = polyA.reversal(k);

      LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;

      boolean testPassed = result.equals(expectedResult);
      if(testPassed){
         System.out.println("Test 1: Passed!");
      }
      else{
         System.out.println("Test 1: Failed!  Expected Result: " + expectedResult + " result: " + result);
	  }

      assertEquals(testPassed, true);
   }
   @Test (expected=ParserException.class)
   public void testNegativePolyReversal(){
      System.out.println("Test 2: Reversal with negative k");
      int k = -5;
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < 15; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.reversal(k);
   }
   @Test
   public void testkLargerthanPolyDegree(){
      System.out.println("Test 3: Reversal where k > the polynomial degree");
      int k = 15;
      int loopCounter = 10; 
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < loopCounter; i++){
         int randomZero = (int) Math.random()*5;
         if(randomZero == 2){
            intList.add(LongInteger.ZERO);
         }
         else{
            intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);

      LongIntegerPolynomial result = polyA.reversal(k);
      ArrayList<LongInteger> resultList = (ArrayList<LongInteger>)result.coefficients();
	  ArrayList<LongInteger> nonZeroResultList = new ArrayList<LongInteger>(resultList.subList(k-polyA.degree(), resultList.size()));
      //Reverse int list
      Collections.reverse(intList);

      boolean allTrue = true;
      for(int i = 0; i < resultList.size(); i++){
         //first k-this.degree() values are 0
		 if(i < (k-polyA.degree())){
			 if(!resultList.get(i).equals(LongInteger.ZERO)){
				 System.out.println("Position " + i +" should be zero.");
				 allTrue = false;
				 break;
				 
			 }
			 else{
				 
			 }
		 }
      }
      assertEquals(allTrue, true);
   }
   @Test
   public void testkSmallerthanPolyDegree(){
      System.out.println("Test 4: Reversal where k < the polynomial degree");
      int k = 2; 
        
      LongInteger[] intList = {LongInteger.valueOf(1), LongInteger.valueOf(2), LongInteger.valueOf(3), LongInteger.valueOf(4)};
      LongInteger[] modList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ZERO, LongInteger.ONE}; 
      LongInteger.changeModulus(LongInteger.valueOf(5));
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));

      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.reversal(k);
	  
	  //System.out.println(result);
	  
      LongInteger[] expectedList = {LongInteger.valueOf(3), LongInteger.valueOf(2), LongInteger.valueOf(2)};
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(expectedList);
	  //System.out.println(expectedResult);
      boolean testPassed = result.equals(expectedResult);

      assertEquals(testPassed, true);
      LongInteger.changeModulus(LongInteger.ZERO);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
   }
   @Test
   public void testReversalkSameAsDegree(){
      System.out.println("Test 5: Reversal works with random polynomial, and k is ther same as the degree");

      int loopCounter = (int) (Math.random()*10)+10;
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < loopCounter; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);

      LongIntegerPolynomial result = polyA.reversal(polyA.degree());
      boolean allTrue = true;

      ArrayList<LongInteger> resultList = (ArrayList<LongInteger>)result.coefficients();
      Collections.reverse(intList);

      for(int i =0; i < resultList.size(); i++){
        if(!resultList.get(i).equals(intList.get(i))){
            allTrue = false;
            break;
        }   
      }
      assertEquals(allTrue, true);
   }
}
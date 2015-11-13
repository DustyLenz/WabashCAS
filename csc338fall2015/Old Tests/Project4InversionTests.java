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
public class Project4InversionTests {
   public Project4InversionTests(){

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
   
   @Test (expected=ParserException.class)
   public void testInversionlZero(){
      System.out.println("Test 6: Inversion with l=0");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.ONE);
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(0);
      
   }
   @Test (expected=ParserException.class)
   public void testInversionlNegative(){
      System.out.println("Test 7: Inversion with l < 0");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.ONE);
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(-5);
   }
   @Test (expected=ParserException.class)
   public void testInversionZero(){
      System.out.println("Test 8: Inversion with 0 polynomial");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;

      LongIntegerPolynomial result = polyA.inversion(7);
   }
   @Test (expected=ParserException.class)
   public void testInversionf0NotOne(){
      System.out.println("Test 9: Inversion with constant term not equal to one");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(5));
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(5);      
   }
   @Test
   public void testInversionWithOne(){
      System.out.println("Test 10: Inversion with 1 at a random l");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ONE;
      int l = (int) Math.random()*9+1;
      LongIntegerPolynomial result = polyA.inversion(1);
      boolean testPassed = result.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
   }
   @Test
   public void testInversionPolynomialNegCoeff(){
      System.out.println("Test 11: inversion with all negative coefficients");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
      for(int i = 0; i < 6; i++){
         if(i == 3){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp.negate());
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(5);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
	  //Check: f*f^-1 = 1 mod x^l
	  LongIntegerPolynomial check = result.multiply(polyA);
	  //System.out.println("check: " + check);
      boolean testPassed = check.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
      LongInteger.changeModulus(LongInteger.ZERO);
   }
   @Test
   public void testInversionWithPositivePolynomial(){
      System.out.println("Test 12: Inverting a random positive coefficient polynomnial");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
      for(int i = 0; i < 6; i++){
         if(i == 3){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp);
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(5);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
	  //Check: f*f^-1 = 1 mod x^l
	  LongIntegerPolynomial check = result.multiply(polyA);
	  System.out.println("check: " + check);
      boolean testPassed = check.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
      LongInteger.changeModulus(LongInteger.ZERO);
   }
}
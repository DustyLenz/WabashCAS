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
public class Project5PolyInversionTests {
   public Project5PolyInversionTests(){

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
	  //p = x^2+1
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  
      LongIntegerPolynomial result = polyA.inversion(p, 0, polyA.remainder(p));
      
   }
   @Test (expected=ParserException.class)
   public void testInversionlNegative(){
      System.out.println("Test 7: Inversion with l < 0");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.ONE);
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
	  //p = x^2+1
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  
      LongIntegerPolynomial result = polyA.inversion(p, -5, polyA.remainder(p));
   }
   @Test (expected=ParserException.class)
   public void testInversionZero(){
      System.out.println("Test 8: Inversion with 0 polynomial");
      
	  //p = x^2+1
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;
	  
      LongIntegerPolynomial result = polyA.inversion(p, 0, LongIntegerPolynomial.ZERO);
   }
   @Test
   public void testInversionWithOne(){
      System.out.println("Test 10: Inversion with 1 at a random l");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ONE;
      int l = (int) Math.random()*9+1;
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
      LongIntegerPolynomial result = polyA.inversion(p, 1, LongIntegerPolynomial.ONE);
      boolean testPassed = result.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
   }
   @Test
   public void testInversionPolynomialNegCoeff(){
      System.out.println("Test 11: inversion with all negative coefficients");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < 6; i++){
         if(i == 3){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp.negate());
         }
      }
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(p, 5, polyA);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(p.pow(LongInteger.valueOf(5))));
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
      for(int i = 0; i < 6; i++){
         if(i == 3){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp);
         }
      }
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(p, 5, polyA);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(p.pow(LongInteger.valueOf(5))));
	  //Check: f*f^-1 = 1 mod x^l
	  LongIntegerPolynomial check = result.multiply(polyA);
	  //System.out.println("check: " + check);
      boolean testPassed = check.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
      LongInteger.changeModulus(LongInteger.ZERO);
   }
}
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
	  LongInteger.changeModulus(LongInteger.valueOf(17));
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      
	  intList.add(LongInteger.valueOf(-187));
	  intList.add(LongInteger.valueOf(-2));
	  intList.add(LongInteger.valueOf(-5));
	  intList.add(LongInteger.valueOf(-34));
	  intList.add(LongInteger.valueOf(0));
	  intList.add(LongInteger.valueOf(-125));
	  
	  
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	 
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  
	  LongInteger[] g0List = {LongInteger.valueOf(14), LongInteger.valueOf(2)};
	  LongIntegerPolynomial g0 = new LongIntegerPolynomial(g0List);
	  
      LongIntegerPolynomial result = polyA.inversion(p, 5, g0);
	  //System.out.println("p: " + p);
	  //System.out.println("polyA: " + polyA);
	  //System.out.println("result: " + result);
	  
	  
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
	  LongInteger.changeModulus(LongInteger.valueOf(17));
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      
	  //Testing x^19 polynomial cause I hate myself
	  //Coefficients generated from random.org
	  intList.add(LongInteger.valueOf(23));
	  intList.add(LongInteger.valueOf(17));
	  intList.add(LongInteger.valueOf(40));
	  intList.add(LongInteger.valueOf(0));
	  intList.add(LongInteger.valueOf(79));
	  intList.add(LongInteger.valueOf(46));
	  intList.add(LongInteger.valueOf(10));
	  intList.add(LongInteger.valueOf(14));
	  intList.add(LongInteger.valueOf(86));
	  intList.add(LongInteger.valueOf(57));
	  intList.add(LongInteger.valueOf(35));
	  intList.add(LongInteger.valueOf(61));
	  intList.add(LongInteger.valueOf(37));
	  intList.add(LongInteger.valueOf(56));
	  intList.add(LongInteger.valueOf(19));
	  intList.add(LongInteger.valueOf(27));
	  intList.add(LongInteger.valueOf(32));
	  intList.add(LongInteger.valueOf(0));
	  intList.add(LongInteger.valueOf(34));
	  intList.add(LongInteger.valueOf(16));
	  intList.add(LongInteger.valueOf(38));
	  
	  LongInteger[] pList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE};
	  LongIntegerPolynomial p = new LongIntegerPolynomial(pList);
	  
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
	  
	  LongInteger[] g0List = {LongInteger.valueOf(10), LongInteger.valueOf(8)};
	  LongIntegerPolynomial g0 = new LongIntegerPolynomial(g0List);
	  
      LongIntegerPolynomial result = polyA.inversion(p, 7, g0);
	  
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
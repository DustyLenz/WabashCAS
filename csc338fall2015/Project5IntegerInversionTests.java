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
public class Project5IntegerInversionTests {
   public Project5IntegerInversionTests(){

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
      LongInteger intA = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
	  LongInteger g0 = LongInteger.ONE;
	  LongInteger p = LongInteger.valueOf(5);
      LongInteger result = intA.inversion(p, 0, g0);
   }
   @Test (expected=ParserException.class)
   public void testInversionlNegative(){
      System.out.println("Test 7: Inversion with l < 0");
      LongInteger intA = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
	  LongInteger g0 = LongInteger.ONE;
	  LongInteger p = LongInteger.valueOf(5);
      LongInteger result = intA.inversion(p, -4, g0);
   }
   @Test (expected=ParserException.class)
   public void testInversionZero(){
      System.out.println("Test 8: Inversion with 0 ");
      LongInteger intA = LongInteger.ZERO;
	  LongInteger result  = intA.inversion(LongInteger.valueOf(5), 5, LongInteger.ZERO);
   }
   @Test
   public void testInversionOne(){
       System.out.println("Test 7: Inversion with one");
	   LongInteger intA = LongInteger.ONE;
	   LongInteger p = LongInteger.valueOf(3);
	   int l = 15;
	   //3^15 = 14348907, a 8 digit number
	   LongInteger g0 = LongInteger.ONE;
	   LongInteger returnValue = intA.inversion(p, l, g0);
	   
	   boolean testPassed = returnValue.equals(LongInteger.ONE);
	   assertEquals(testPassed, true);
   }
   @Test
   public void testInversionNegativeInt(){
	   System.out.println("Test 7: Inversion with negative integer");
	   LongInteger temp = LongInteger.valueOf("-" + CSC338Utils.generateNDigitNumber(5));
	   
	   //Force number to have g0 = 1	   
	   LongInteger intA = (temp.multiply(LongInteger.valueOf(3))).add(LongInteger.ONE);
	   
	   
	   LongInteger p = LongInteger.valueOf(3);
	   int l = 15;
	   //3^15 = 14348907, a 8 digit number

	   LongInteger g0 = LongInteger.ONE;
	   //System.out.println("intA: " + intA);
	   
	   LongInteger returnValue = intA.inversion(p, l, g0);
	   LongInteger.changeModulus(p.pow(LongInteger.valueOf(l)));
	   LongInteger result = returnValue.multiply(intA);
	   
	   boolean testPassed = result.equals(LongInteger.ONE);
	   assertEquals(testPassed, true);
   }
   @Test
   public void testInversionPositiveInt(){
	   System.out.println("Test 7: Inversion with positive integer");
	   LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
	   
	   //Force number so that g0 = 2
	   
	   LongInteger intA = (temp.multiply(LongInteger.valueOf(3))).add(LongInteger.valueOf(2));
	   
	   LongInteger p = LongInteger.valueOf(3);
	   int l = 15;
	   //3^15 = 14348907, a 8 digit number
	   LongInteger g0 = LongInteger.valueOf(2);
	   LongInteger returnValue = intA.inversion(p, l, g0);
	   LongInteger.changeModulus(p.pow(LongInteger.valueOf(l)));
	   LongInteger result = returnValue.multiply(intA);
	   
	   boolean testPassed = result.equals(LongInteger.ONE);
	   assertEquals(testPassed, true);
   }
}
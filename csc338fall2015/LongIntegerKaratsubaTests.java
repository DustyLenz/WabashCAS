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
public class LongIntegerKaratsubaTests {
   public LongIntegerKaratsubaTests(){

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
   public void testKaratsubaMultiplyZero(){
      System.out.println("Test: Karatsuba multiply by zero");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = LongInteger.ZERO;
      LongInteger result = a.karatsuba(LongInteger.ZERO);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testKaratsubaMultiplyOne(){
      System.out.println("Test: Karatsuba multiply by one");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));
      LongInteger expectedResult = new LongInteger(a);
      LongInteger result = a.karatsuba(LongInteger.ONE);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testKaratsbuaCommutative(){
      System.out.println("Test: Karatsuba Commutative property");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));
      LongInteger b = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = a.karatsuba(b);
      LongInteger result = b.karatsuba(a);
      assertEquals(result, expectedResult);
   }
   @Test
   public void testKaratsubaPositiveMultiplyPositive(){
      System.out.println("Test: Karatsuba multiplying two positive numbers");
      String valA = CSC338Utils.generateNDigitNumber(5);
      String valB = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger b = LongInteger.valueOf(valB);

      LongInteger result = a.karatsuba(b);
      LongInteger expectedResult = a.multiply(b);

      assertEquals(result, expectedResult);
   }
   @Test
   public void testKaratsubaNegativeMultiplyNegative(){  
      System.out.println("Test: Karatsuba multiplying two negative numbers");
      String valA = CSC338Utils.generateNDigitNumber(5);
      String valB = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger b = LongInteger.valueOf(valB);
      a.negate();
      b.negate();
      LongInteger result = a.karatsuba(b);
      LongInteger expectedResult = a.multiply(b);
      assertEquals(result, expectedResult);   
   }
   @Test
   public void testKaratsubaNegativeMultiplyPositive(){ 
      System.out.println("Test: Karatsuba multiplying a negative number by a positive number");
      String valA = CSC338Utils.generateNDigitNumber(5);
      String valB = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger b = LongInteger.valueOf(valB);
      a.negate();
      LongInteger result = a.karatsuba(b);
      LongInteger expectedResult = a.multiply(b);
   }
   
}
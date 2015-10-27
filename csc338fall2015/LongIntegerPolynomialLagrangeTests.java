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
public class LongIntegerPolynomialLagrangeTests {
   public LongIntegerPolynomialLagrangeTests(){

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
   public void testlagrangeAllZero(){
      System.out.println("Test: Lagrange interpolant of a bunch of 0 points");
      LongInteger[] input = new LongInteger[10];
      Arrays.fill(input, LongInteger.ZERO);
      LongIntegerPolynomial[] result = LongIntegerPolynomial.lagrangeInterpolants(input);
      boolean allTrue = true;
      for(int i = 0; i < result.length; i++){
         if(!result[0].equals(LongIntegerPolynomial.ZERO)){
            allTrue = false;
            break;
         }
      }
      assertEquals(allTrue, true);
   }
   public void testInterpolantWorks(){
      System.out.println("Test: polynomial interpolate function works");
      LongInteger.changeModulus(LongInteger.valueOf(17));
      LongInteger[] uPoints = {LongInteger.valueOf(-1), LongInteger.valueOf(0), LongInteger.valueOf(1), LongInteger.valueOf(2), LongInteger.valueOf(6)};
      LongInteger[] vPoints = {LongInteger.valueOf(2), LongInteger.valueOf(5), LongInteger.valueOf(6), LongInteger.valueOf(15), LongInteger.valueOf(4)};
      LongIntegerPolynomial result = LongIntegerPolynomial.interpolate(uPoints, vPoints);

      LongInteger[] answerList = {LongInteger.valueOf(5), LongInteger.valueOf(2), LongInteger.valueOf(2), LongInteger.valueOf(14)};
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
      assertEquals(result, expectedResult);
   }   
}

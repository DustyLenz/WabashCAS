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
public class LongIntegerPolynomialEvalTests {
   public LongIntegerPolynomialEvalTests(){

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
   
   //Only one test for evaluate, it's literally just apply on multiple values
   @Test 
   public void testPolynomialEvaluate(){
      System.out.println("Test: polynomial evaluate function works");
      int[] inputIntList = {1, 3, 0, 27, 15, 0, 0, 3, 0, 4, 0, 5};
      ArrayList<LongInteger> inputList = new ArrayList<LongInteger>();
      for(int i = 0; i < inputIntList.length; i++){
         inputList.add(LongInteger.valueOf(inputIntList[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(inputList);
	  System.out.println("PolyA degree: " + polyA.degree());
      LongInteger[] valueList = new LongInteger[3];
      valueList[0] = LongInteger.valueOf(5);
      valueList[1] = LongInteger.valueOf(7);
      valueList[2] = LongInteger.valueOf(11);

      LongInteger[] result = polyA.evaluate(valueList);
      LongInteger[] expectedResult = new LongInteger[3];
      expectedResult[0] = LongInteger.valueOf("252200266");
      expectedResult[1] = LongInteger.valueOf("10050564070");
      expectedResult[2] = LongInteger.valueOf("1436048860918");
      boolean allTrue = true;
      for(int i = 0; i < result.length; i++){
         if(!result[i].equals(expectedResult[i])){
			System.out.println("result[i]: " + result[i] + " expectedResult[i]: " + expectedResult[i]);
            allTrue = false;
			break;
         }
      }
      assertEquals(allTrue, true);
   }
   
   
}

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
public class LongIntegerPowEvalTests {
   public LongIntegerPowEvalTests(){

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
   public void testIntZerothPower(){
	  System.out.println("Test: Raise integer to 0th power");
      String valA = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger expectedResult = LongInteger.ONE; 
      LongInteger result = a.pow(LongInteger.ZERO);
	  
	  
      assertEquals(expectedResult, result);
   }
   @Test
   public void testIntFirstPower(){
      System.out.println("Test: Raise integer to 1st power");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
      LongInteger expectedResult = a;
      LongInteger result = a.pow(LongInteger.ONE);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testIntNegativePower(){
      System.out.println("Test: Raise integer to a random negative power");

      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
      LongInteger b = LongInteger.valueOf("-"+CSC338Utils.generateNDigitNumber(2));
	  LongInteger.changeModulus(LongInteger.valueOf(101));
	  //System.out.println("negB.positive(): " + b.positive());
      LongInteger result = a.pow(b);
      LongInteger expectedResult = a.inverse().pow(b.negate());
	  
	  boolean testPassed = expectedResult.equals(result);
	  if(!testPassed){
		System.out.println("a: " + a + " b: " + b);
		System.out.println("result: " + result + " expectedResult: " + expectedResult);
	  }
      assertEquals(testPassed, true);
      LongInteger.changeModulus(LongInteger.ZERO);
   }
   @Test
   public void testIntRandomPower(){
      System.out.println("Test: Raise integer to a random positive power");
      int pow = 5;
      String valA = CSC338Utils.generateNDigitNumber(6);
      BigInteger bigIntA = new BigInteger(valA);
      LongInteger a = LongInteger.valueOf(valA);
      BigInteger expectedResult = bigIntA.pow(pow);
	  //System.out.println("result: " + result + " expectedResult: " + expectedResult);
      LongInteger result = a.pow(LongInteger.valueOf(pow));
      assertEquals(expectedResult.toString(), result.toString());
   }
   @Test
   public void testIntEvaluateModEqualslNumber(){
      System.out.println("Test: Evaluate a number mod itself");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      LongInteger[] moduli = new LongInteger[1];
      moduli[0]= a;
      LongInteger expectedResult = new LongInteger(a.evaluate(moduli)[0]);
      a.changeModulus(a);
      a = a.add(LongInteger.ZERO);
	  
	  boolean testPassed = a.equals(expectedResult);
	  
      assertEquals(testPassed, true);
   }
   @Test
   public void testIntEvaluate(){
      System.out.println("Test: Evaluate a number mod 5 random numbers");
      LongInteger[] moduli = new LongInteger[5];
      String[] vals = new String[5];
      vals[0] = CSC338Utils.generateNDigitNumber(2);
      vals[1] = CSC338Utils.generateNDigitNumber(2);
      vals[2] = CSC338Utils.generateNDigitNumber(2);
      vals[3] = CSC338Utils.generateNDigitNumber(2);
      vals[4] = CSC338Utils.generateNDigitNumber(2);

      moduli[0] = LongInteger.valueOf(vals[0]);
      moduli[1] = LongInteger.valueOf(vals[1]);
      moduli[2] = LongInteger.valueOf(vals[2]);
      moduli[3] = LongInteger.valueOf(vals[3]);
      moduli[4] = LongInteger.valueOf(vals[4]);
      String stringValue = CSC338Utils.generateNDigitNumber(5);
      LongInteger value = LongInteger.valueOf(stringValue);
      LongInteger[] result = value.evaluate(moduli); 
      boolean allTrue = true;
      for(int i =0 ; i< result.length; i++){
         BigInteger temp = new BigInteger(stringValue);
		 BigInteger tempMod = new BigInteger(vals[i]);
         if(!(temp.mod(tempMod).toString().equals(result[i].toString()))){
			System.out.println("tempMod: " + tempMod + " result[i]: "  + result[i]);
            allTrue = false;
            break;
         }
      }
      assertEquals(true, allTrue);
   }
   
}
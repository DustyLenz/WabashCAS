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
public class LongIntegerPolynomialPowApplyTests {
   public LongIntegerPolynomialPowApplyTests(){

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
   public void testPolynomialZerothPower(){
      System.out.println("Test: raise polynomial to 0th power");
      ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
      for(int i = 0; i < 15; i++){
         if(i == 3 || i == 5){
            polyList.add(LongInteger.ZERO);
         }
         else{
            polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
      LongIntegerPolynomial result = polyA.pow(LongInteger.ZERO);
      assertEquals(result, LongIntegerPolynomial.ONE);
   }
   @Test
   public void testPolynomialFirstPower(){
      System.out.println("Test: raise polynomial to 1st power");
      ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
      for(int i = 0; i < 6; i++){
         if(i == 3){
            polyList.add(LongInteger.ZERO);
         }
         else{

            polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);
      LongIntegerPolynomial result = polyA.pow(LongInteger.ONE);
      assertEquals(polyA, result);
   }
   @Test
   public void testPolynomialPowWorks(){
	  LongInteger.changeModulus(LongInteger.ZERO);
      System.out.println("Test: raise polynomial to random power");
      //Answer from mathematica
      int[] answerIntList ={243, 0, 1620, 405, 4320, 2160, 6030, 4320, 4920, 3930, 2464, 1520, 655, 160, 20, 1};
      ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();

      for(int i = 0; i < answerIntList.length; i++){
         answerList.add(LongInteger.valueOf(answerIntList[i]));
      }
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
      int[] inputArray = {3, 0, 4, 1};
      ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
      for(int i = 0; i < inputArray.length; i++){
         polyList.add(LongInteger.valueOf(inputArray[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);

      LongIntegerPolynomial result = polyA.pow(LongInteger.valueOf(5));
	  boolean testPassed = result.equals(expectedResult);
	  if(!testPassed){
	     System.out.println("result: " + result + " expectedResult: " + expectedResult);
	  }
      assertEquals(testPassed, true);

   }
   @Test
   public void testPolynomialNegativePowWorks(){
      System.out.println("Test: raise polynomial to negative random power");
	  LongInteger.changeModulus(LongInteger.valueOf(17));
	  LongInteger [] tempNewModList = {LongInteger.valueOf(1), LongInteger.valueOf(0), LongInteger.valueOf(1)};
	  LongIntegerPolynomial newMod = new LongIntegerPolynomial(tempNewModList);
	  LongIntegerPolynomial.changeModulus(newMod);
	  
      //Answer from mathematica
      int[] answerIntList ={4,4};
      ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();

      for(int i = 0; i < answerIntList.length; i++){
         answerList.add(LongInteger.valueOf(answerIntList[i]));
      }
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
      int[] inputArray = {3, 0, 4, 1};
      ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
      for(int i = 0; i < inputArray.length; i++){
         polyList.add(LongInteger.valueOf(inputArray[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);

      LongIntegerPolynomial result = polyA.pow(LongInteger.valueOf(-11));

      boolean testPassed = result.equals(expectedResult);
      assertEquals(testPassed, true);
	  LongInteger.changeModulus(LongInteger.ZERO);
	  LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
   }
   @Test
   public void testPolynomialApplyZero(){ 
      System.out.println("Test: apply polynomial with value 0");
      ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
      for(int i = 0; i < 6; i++){
         if(i == 3){
            polyList.add(LongInteger.ZERO);
         }
         else{

            polyList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(polyList);

      LongInteger result = polyA.apply(LongInteger.ZERO);

      LongInteger expectedResult = polyList.get(0);
	  boolean testPassed = result.equals(expectedResult);
      assertEquals(testPassed, true);
   }
   @Test
   public void testPolynomialApplyWorks(){
      System.out.println("Test: polynomial apply function works");
      int[] inputIntList = {1, 3, 0, 27, 15, 0, 0, 3, 0, 4, 0, 5};
      ArrayList<LongInteger> inputList = new ArrayList<LongInteger>();
      for(int i = 0; i < inputIntList.length; i++){
         inputList.add(LongInteger.valueOf(inputIntList[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(inputList);

      LongInteger result = polyA.apply(LongInteger.valueOf(5));

      LongInteger expectedResult = LongInteger.valueOf("252200266");
	  boolean testPassed = result.equals(expectedResult);
      assertEquals(testPassed, true);
   }
   
}


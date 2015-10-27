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
      System.out.println("Test: raise polynomial to random power");
      //Answer from mathematica
      int[] answerIntList ={1, 20, 160, 655, 1520, 2464, 3930, 4920, 4320, 6030, 2160, 4320, 405, 1620, 0, 243};
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

      assertEquals(expectedResult, result);

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

      assertEquals(expectedResult, result);

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

      LongInteger expectedResult = LongInteger.ZERO;

      assertEquals(expectedResult, result);
   }
   @Test
   public void testPolynomialApplyWorks(){
      System.out.println("Test: polynomial apply function works");
      int[] inputIntList = {5, 0, 4, 0, 3, 0, 0, 15, 27, 0, 3, 1};
      ArrayList<LongInteger> inputList = new ArrayList<LongInteger>();
      for(int i: inputIntList){
         inputList.add(LongInteger.valueOf(inputIntList[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(inputList);

      LongInteger result = polyA.apply(LongInteger.valueOf(5));

      LongInteger expectedResult = LongInteger.valueOf("252200266");

      assertEquals(result, expectedResult);
   }
   
}


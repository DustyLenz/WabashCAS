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
public class Project3Tests {
   public Project3Tests(){

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
      String valA = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger expectedResult = LongInteger.ONE; 
      LongInteger result = a.pow(LongInteger.ZERO);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testIntFirstPower(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
      LongInteger expectedResult = a;
      LongInteger result = a.pow(LongInteger.ONE);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testIntNegativePower(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(5));
      LongInteger b = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      b.negate();
      LongInteger result = a.pow(b);
      b.negate();
      LongInteger expectedResult = a.inverse().pow(b);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testIntRandomPower(){
      int pow = 5;
      String valA = CSC338Utils.generateNDigitNumber(6);
      BigInteger bigIntA = new BigInteger(valA);
      LongInteger a = LongInteger.valueOf(valA);
      BigInteger expectedResult = bigIntA.pow(pow);
      LongInteger result = a.pow(LongInteger.valueOf(pow));
      assertEquals(expectedResult.toString(), result.toString());
   }
   @Test
   public void testIntEvaluateModEqauslNumber(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      LongInteger[] moduli = new LongInteger[1];
      moduli[0]= a;
      LongInteger expectedResult = new LongInteger(a.evaluate(moduli)[0]);
      a.changeModulus(a);
      a = a.add(LongInteger.ZERO);
      assertEquals(a, expectedResult);
   }
   @Test
   public void testIntEvaluate(){
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
         if(!(temp.mod(new BigInteger(vals[i])).toString().equals(moduli[i].toString()))){
            allTrue = false;
            break;
         }
      }
      assertEquals(true, allTrue);
   }
   @Test
   public void testCRA(){ 
      LongInteger[] moduli = new LongInteger[5];
      LongInteger[] vals = new LongInteger[5];
      vals[0] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[1] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[2] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[3] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[4] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));

      moduli[0] = LongInteger.valueOf(313);
      moduli[1] = LongInteger.valueOf(373);
      moduli[2] = LongInteger.valueOf(571);
      moduli[3] = LongInteger.valueOf(661);
      moduli[4] = LongInteger.valueOf(773);

      LongInteger result = LongInteger.cra(vals, moduli);
      boolean allTrue = true;
      for(int i = 0; i < vals.length; i++){
         BigInteger tempBig = new BigInteger(result.toString());
         allTrue =(vals[i].toString()).equals(tempBig.mod(new BigInteger(moduli[i].toString())));
         if(!allTrue){
            break;
         }
      }
      assertEquals(allTrue, true);

   }
   @Test
   public void testKaratsubaMultiplyZero(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = LongInteger.ZERO;
      LongInteger result = a.karatsuba(LongInteger.ZERO);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testKaratsubaMultiplyOne(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));
      LongInteger expectedResult = new LongInteger(a);
      LongInteger result = a.karatsuba(LongInteger.ONE);
      assertEquals(expectedResult, result);
   }
   @Test
   public void testKaratsbuaCommutative(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));
      LongInteger b = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = a.karatsuba(b);
      LongInteger result = b.karatsuba(a);
      assertEquals(result, expectedResult);
   }
   @Test
   public void testKaratsubaPositiveMultiplyPositive(){
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
      String valA = CSC338Utils.generateNDigitNumber(5);
      String valB = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger b = LongInteger.valueOf(valB);
      a.negate();
      LongInteger result = a.karatsuba(b);
      LongInteger expectedResult = a.multiply(b);
   }
   @Test
   public void testPolynomialZerothPower(){
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
   public void testPolynomialApplyZero(){ 
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
   //Only one test for evaluate, it's literally just apply on multiple values
   @Test 
   public void testPolynomialEvaluate(){
      int[] inputIntList = {5, 0, 4, 0, 3, 0, 0, 15, 27, 0, 3, 1};
      ArrayList<LongInteger> inputList = new ArrayList<LongInteger>();
      for(int i: inputIntList){
         inputList.add(LongInteger.valueOf(inputIntList[i]));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(inputList);
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
            allTrue = false;
         }
      }
      assertEquals(allTrue, true);

   }
   @Test
   public void testlagrangeAllZero(){
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
   /*@Test
   //Handworked example... nothing online can calculates only the Lagrange interpolant
   public void testLagrangeWorks(){
      LongInteger.changeModulus(LongInteger.valueOf(5));
      LongInteger[] point = new LongInteger[3];
      point[0] = LongInteger.valueOf(2);
      point[1] = LongInteger.valueOf(4);
      point[2] = LongInteger.valueOf(5);
      
      LongIntegerPolynomial[] result = LongIntegerPolynomial.lagrangeInterpolants(point);
      LongIntegerPolynomial[] expectedResult = new LongIntegerPolynomial[3];
      LongInteger[] temp = {LongInteger.valueOf(20), LongInteger.valueOf(-9), LongInteger.valueOf(1)};
      expectedResult[0] = new LongIntegerPolynomial(temp);
      LongInteger[] temp1 = {LongInteger.valueOf(20), LongInteger.valueOf(-14), LongInteger.valueOf(2)};
      expectedResult[1] = new LongIntegerPolynomial(temp1);
      LongInteger[] temp2 = {LongInteger.valueOf(16), LongInteger.valueOf(-12), LongInteger.valueOf(2)};
      expectedResult[2] = new LongIntegerPolynomial(temp2);
      boolean allTrue = true;
      for(int i = 0; i < result.length; i++){
         if(!result[0].equals(expectedResult[i])){
            allTrue = false;
            break;
         }
      }
      assertEquals(allTrue, true);
   }*/
   @Test
   //interpolate calls lagrange interpolant, we can test both at the same time
   public void testInterpolantWorks(){
      LongInteger.changeModulus(LongInteger.valueOf(17));
      LongInteger[] uPoints = {LongInteger.valueOf(-1), LongInteger.valueOf(0), LongInteger.valueOf(1), LongInteger.valueOf(2), LongInteger.valueOf(6)};
      LongInteger[] vPoints = {LongInteger.valueOf(2), LongInteger.valueOf(5), LongInteger.valueOf(6), LongInteger.valueOf(15), LongInteger.valueOf(4)};
      LongIntegerPolynomial result = LongIntegerPolynomial.interpolate(uPoints, vPoints);
      
      LongInteger[] answerList = {LongInteger.valueOf(5), LongInteger.valueOf(2), LongInteger.valueOf(2), LongInteger.valueOf(14)};
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
      assertEquals(result, expectedResult);
   }
}


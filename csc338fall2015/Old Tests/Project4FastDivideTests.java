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
public class Project4FastDivideTests {
   public Project4FastDivideTests(){

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
   public void fastdivideByZero(){
      System.out.println("Test 13: Divide by Zero");
      LongIntegerPolynomial divisor = LongIntegerPolynomial.ZERO;
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
      //Make polynomial monic
      intList.add(LongInteger.ONE);
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      
      LongIntegerPolynomial[] result = polyA.fastDivideAndRemainder(divisor);

   }
   @Test
   public void fastDivideBy1(){
      System.out.println("Test 14: Divide by One");
      LongIntegerPolynomial divisor = LongIntegerPolynomial.ONE;
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
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      
      LongIntegerPolynomial[] result = polyA.fastDivideAndRemainder(divisor);
	  //System.out.println("q: " + result[0]);
      boolean testPassed = result[0].equals(polyA);
      //testPassed =  result[1].equals(LongIntegerPolynomial.ZERO);

      assertEquals(testPassed, true);
   }
   @Test
   public void fastDivideSmallDegreebyBigDegree(){
      System.out.println("Test 15: Divide smaller degree by big degree");
      ArrayList<LongInteger> dividendList = new ArrayList<LongInteger>();
      for(int i = 0; i < 3; i++){
         LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
         dividendList.add(temp);
      }
      ArrayList<LongInteger> divisorList = new ArrayList<LongInteger>();
      for(int i = 0; i < 6; i++){
         if(i == 3){
            divisorList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            divisorList.add(temp);
         }
      }
      divisorList.add(LongInteger.ONE);
      LongIntegerPolynomial divisor = new LongIntegerPolynomial(divisorList);
      LongIntegerPolynomial dividend = new LongIntegerPolynomial(dividendList);


      LongIntegerPolynomial[] result = dividend.fastDivideAndRemainder(divisor);

      boolean testPassed = result[0].equals(LongIntegerPolynomial.ZERO);
      testPassed = result[1].equals(dividend);
      assertEquals(testPassed, true);
   }
   @Test
   public void fastDivideNegativeCoeffByPositiveCoeff(){
      System.out.println("Test 16: Divide Negative by Positive Coefficients");
      ArrayList<LongInteger> divisorList = new ArrayList<LongInteger>();
      for(int i = 0; i < 3; i++){
         LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
         divisorList.add(temp);
      }
      divisorList.add(LongInteger.ONE);
      ArrayList<LongInteger> dividendList = new ArrayList<LongInteger>();
      for(int i = 0; i < 8; i++){
         if(i == 3){
            dividendList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            dividendList.add(temp.negate());
         }
      }
      LongIntegerPolynomial divisor = new LongIntegerPolynomial(divisorList);
      LongIntegerPolynomial dividend = new LongIntegerPolynomial(dividendList);
      
	  //System.out.println("Dividend: " + dividend);
	  //System.out.println("Divisot: " + divisor);

      LongIntegerPolynomial[] result = dividend.fastDivideAndRemainder(divisor);
	  //System.out.println("reuslt[0]: " + result[0]);
      //Undo the division
      LongIntegerPolynomial undoResult = (result[0].karatsuba(divisor)).add(result[1]);

      boolean testPassed = undoResult.equals(dividend);
      assertEquals(testPassed, true);

   }
   @Test
   public void fastDividePositiveCoeffByNegativeCoeff(){
      System.out.println("Test 17: Divide Positive by Negative Coefficients");
      ArrayList<LongInteger> divisorList = new ArrayList<LongInteger>();
      for(int i = 0; i < 3; i++){
         LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
         divisorList.add(temp.negate());
      }
      divisorList.add(LongInteger.ONE);
      ArrayList<LongInteger> dividendList = new ArrayList<LongInteger>();
      for(int i = 0; i < 8; i++){
         if(i == 3){
            dividendList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            dividendList.add(temp);
         }
      }
      LongIntegerPolynomial divisor = new LongIntegerPolynomial(divisorList);
      LongIntegerPolynomial dividend = new LongIntegerPolynomial(dividendList);


      LongIntegerPolynomial[] result = dividend.fastDivideAndRemainder(divisor);

      //Undo the division
      LongIntegerPolynomial undoResult = (result[0].karatsuba(divisor)).add(result[1]);

      boolean testPassed = undoResult.equals(dividend);
      assertEquals(testPassed, true);

   }
   @Test
   public void fastDivideNegativeCoeffByNegativeCoeff(){
      System.out.println("Test 18: Divide Negative by Negative Coefficients");
      ArrayList<LongInteger> divisorList = new ArrayList<LongInteger>();
      for(int i = 0; i < 3; i++){
         LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
         divisorList.add(temp.negate());
      }
      divisorList.add(LongInteger.ONE);
      ArrayList<LongInteger> dividendList = new ArrayList<LongInteger>();
      for(int i = 0; i < 8; i++){
         if(i == 3){
            dividendList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            dividendList.add(temp.negate());
         }
      }
      LongIntegerPolynomial divisor = new LongIntegerPolynomial(divisorList);
      LongIntegerPolynomial dividend = new LongIntegerPolynomial(dividendList);


      LongIntegerPolynomial[] result = dividend.fastDivideAndRemainder(divisor);

      //Undo the division
      LongIntegerPolynomial undoResult = (result[0].karatsuba(divisor)).add(result[1]);

      boolean testPassed = undoResult.equals(dividend);
      assertEquals(testPassed, true);

   }
   @Test
   public void fastDividePositiveByPositive(){
      System.out.println("Test 19: Divide Positive by Positive Coefficients");
      ArrayList<LongInteger> divisorList = new ArrayList<LongInteger>();
      for(int i = 0; i < 3; i++){
         LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
         divisorList.add(temp);
      }
      divisorList.add(LongInteger.ONE);
      ArrayList<LongInteger> dividendList = new ArrayList<LongInteger>();
      for(int i = 0; i < 8; i++){
         if(i == 3){
            dividendList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            dividendList.add(temp);
         }
      }
      LongIntegerPolynomial divisor = new LongIntegerPolynomial(divisorList);
      LongIntegerPolynomial dividend = new LongIntegerPolynomial(dividendList);


      LongIntegerPolynomial[] result = dividend.fastDivideAndRemainder(divisor);

      //Undo the division
      LongIntegerPolynomial undoResult = (result[0].karatsuba(divisor)).add(result[1]);

      boolean testPassed = undoResult.equals(dividend);
      assertEquals(testPassed, true);
   }
}
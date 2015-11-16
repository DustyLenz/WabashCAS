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
public class Project4Tests {
   public Project4Tests(){

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
   public void testZeroPolyReversal(){
      System.out.println("Test 1: Reversal with a zero polynomial");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;
      int k = (int) (Math.random()*10)+1;
      LongIntegerPolynomial result = polyA.reversal(k);

      LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;

      boolean testPassed = result.equals(expectedResult);
      if(testPassed){
         System.out.println("Test 1: Passed!");
      }
      else{
         System.out.println("Test 1: Failed!  Expected Result: " + expectedResult + " result: " + result);
   }

      assertEquals(testPassed, true);
   }
   @Test (expected=ParserException.class)
   public void testNegativePolyReversal(){
      System.out.println("Test 2: Reversal with negative k");
      int k = -5;
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < 15; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.reversal(k);
   }
   @Test
   public void testkLargerthanPolyDegree(){
      System.out.println("Test 3: Reversal where k > the polynomial degree");
      int k = 6;
      int loopCounter = 4; 
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      for(int i = 0; i < loopCounter; i++){
         int randomZero = (int) Math.random()*5;
         if(randomZero == 2){
            intList.add(LongInteger.ZERO);
         }
         else{
            intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      
      /*intList.add(LongInteger.valueOf(1));
      intList.add(LongInteger.valueOf(0));
      intList.add(LongInteger.valueOf(1));
      intList.add(LongInteger.valueOf(0));
      intList.add(LongInteger.valueOf(0));
      intList.add(LongInteger.valueOf(1));            
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);*/
      
      //System.out.println("polyA: " + polyA);
      LongIntegerPolynomial result = polyA.reversal(k);
      ArrayList<LongInteger> resultList = new ArrayList<LongInteger>(result.coefficients());
      //System.out.println("Reversed: " + resultList);
      //Reverse int list
      Collections.reverse(intList);
      boolean allTrue = true;
      
      for(int i = 0; i < resultList.size(); i++){
        //System.out.println("i: " + i);
        if(i < (k-polyA.degree())){
          if(!resultList.get(i).equals(LongInteger.ZERO)){
               allTrue = false;
               System.out.println("Expecting 0 at i: " + i);
               break;
            }
        }
        else
        {
            //System.out.println("i-(k-polyA.degree()): " + (i-(k-polyA.degree())));
            if(!resultList.get(i).equals(intList.get(i-(k-polyA.degree())))){
               allTrue = false;
               System.out.println("Mismatch coefficient at i: " + i + " Expecting: " + intList.get(resultList.size()-i-1) + " Result: " + resultList.get(i));
               break;
            }
          }
      }
      assertEquals(allTrue, true);
   }
   @Test
   public void testkSmallerthanPolyDegree(){
      System.out.println("Test 4: Reversal where k < the polynomial degree");
      int k = 2; 
        
      LongInteger[] intList = {LongInteger.valueOf(1), LongInteger.valueOf(2), LongInteger.valueOf(3), LongInteger.valueOf(4)};
      LongInteger[] modList = {LongInteger.ONE, LongInteger.ZERO, LongInteger.ZERO, LongInteger.ONE}; 
      LongInteger.changeModulus(LongInteger.valueOf(5));
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));

      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.reversal(k);
      //System.out.println("result: " + result);
      LongInteger[] expectedList = {LongInteger.valueOf(3), LongInteger.valueOf(2), LongInteger.valueOf(2)};
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(expectedList);

      boolean testPassed = result.equals(expectedResult);

      assertEquals(testPassed, true);
      LongInteger.changeModulus(LongInteger.ZERO);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
   }
   @Test
   //public static void main(String[] args) {
     public void testReversalkSameAsDegree(){
      System.out.println("Test 5: Reversal works with random polynomial, and k is ther same as the degree");

      int loopCounter = (int) (Math.random()*10)+10;
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      
      
      for(int i = 0; i < loopCounter; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      ArrayList<LongInteger> intListCopy = new ArrayList<LongInteger>(intList);
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      //System.out.println("polyA: " + polyA);
      LongIntegerPolynomial result = polyA.reversal(polyA.degree());
      //System.out.println("Result: " + result);
      boolean allTrue = true;

      ArrayList<LongInteger> resultList = (ArrayList<LongInteger>)result.coefficients();
      Collections.reverse(intListCopy);
      //System.out.println("intList: " + intListCopy);
      //System.out.println("resultList: " + resultList + " intList: " + intList);
      for(int i =0; i < resultList.size(); i++){
        if(!resultList.get(i).equals(intListCopy.get(i))){
            allTrue = false;
            break;
        }   
      }
      assertEquals(allTrue, true);
   }
   @Test (expected=ParserException.class)
   public void testInversionlZero(){
      System.out.println("Test 6: Inversion with l=0");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.ONE);
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(0);
      
   }
   @Test (expected=ParserException.class)
   public void testInversionlNegative(){
      System.out.println("Test 7: Inversion with l < 0");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.ONE);
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(-5);
   }
   @Test (expected=ParserException.class)
   public void testInversionZero(){
      System.out.println("Test 8: Inversion with 0 polynomial");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ZERO;

      LongIntegerPolynomial result = polyA.inversion(7);
   }
   @Test (expected=ParserException.class)
   public void testInversionf0NotOne(){
      System.out.println("Test 9: Inversion with constant term not equal to one");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(5));
      for(int i = 0; i < 6; i++){
         intList.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(5);      
   }
   @Test
   public void testInversionWithOne(){
      System.out.println("Test 10: Inversion with 1 at a random l");
      LongIntegerPolynomial polyA = LongIntegerPolynomial.ONE;
      int l = (int) Math.random()*9+1;
      LongIntegerPolynomial result = polyA.inversion(1);
      boolean testPassed = result.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
   }
   @Test
   public void testInversionPolynomialNegCoeff(){
      System.out.println("Test 11: inversion with all negative coefficients");
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
      for(int i = 0; i < 6; i++){
         if(i == 3){
            intList.add(LongInteger.ZERO);
         }
         else{
            LongInteger temp = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
            intList.add(temp.negate());
         }
      }
      LongIntegerPolynomial polyA = new LongIntegerPolynomial(intList);
      LongIntegerPolynomial result = polyA.inversion(5);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
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
      ArrayList<LongInteger> intList = new ArrayList<LongInteger>();
      intList.add(LongInteger.valueOf(1));
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
      LongIntegerPolynomial result = polyA.inversion(5);
	  
	  ArrayList<LongInteger> modList = new ArrayList<LongInteger>(Collections.nCopies(5, LongInteger.ZERO));
      modList.add(LongInteger.ONE);
      LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
	  //Check: f*f^-1 = 1 mod x^l
	  LongIntegerPolynomial check = result.multiply(polyA);
	  //System.out.println("check: " + check);
      boolean testPassed = check.equals(LongIntegerPolynomial.ONE);
      assertEquals(testPassed, true);
      LongIntegerPolynomial.changeModulus(LongIntegerPolynomial.ZERO);
      LongInteger.changeModulus(LongInteger.ZERO);
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

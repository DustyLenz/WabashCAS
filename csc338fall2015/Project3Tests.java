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
   public void testCRA(){
      System.out.println("Test: Integer CRA");
      LongInteger[] moduli = new LongInteger[5];
      LongInteger[] vals = new LongInteger[5];
      vals[0] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[1] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[2] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[3] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
      vals[4] = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2));
	  
	  /*vals[0] = LongInteger.valueOf(10);
      vals[1] = LongInteger.valueOf(10);
      vals[2] = LongInteger.valueOf(10);
      vals[3] = LongInteger.valueOf(10);
      vals[4] = LongInteger.valueOf(10);*/
	  

      moduli[0] = LongInteger.valueOf(313);
      moduli[1] = LongInteger.valueOf(373);
      moduli[2] = LongInteger.valueOf(571);
      moduli[3] = LongInteger.valueOf(661);
      moduli[4] = LongInteger.valueOf(773);

      LongInteger result = LongInteger.cra(vals, moduli);
      boolean allTrue = true;
      for(int i = 0; i < vals.length; i++){
         LongInteger tempChecker = result.remainder(moduli[i]);
         allTrue = tempChecker.equals(vals[i]);
         if(!allTrue){
			System.out.println("i: "+ i + " tempCheck : " + tempChecker +  " vals[i]: " + vals[i]);
            break;
         }
      }
      assertEquals(allTrue, true);

   }
   
   @Test
   public void testKaratsubaMultiplyZero(){
      System.out.println("Test: Karatsuba multiply by zero");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = LongInteger.ZERO;
      LongInteger result = a.karatsuba(LongInteger.ZERO);
      assertEquals(expectedResult.toString(), result.toString());
   }
   @Test
   public void testKaratsubaMultiplyOne(){
      System.out.println("Test: Karatsuba multiply by one");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(6));
      LongInteger expectedResult = new LongInteger(a);
      LongInteger result = a.karatsuba(LongInteger.ONE);
	  
	  boolean testPassed = expectedResult.equals(result);
	  
      assertEquals(testPassed, true);
   }
   @Test
   public void testKaratsbuaCommutative(){
      System.out.println("Test: Karatsuba Commutative property");
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));
      LongInteger b = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = a.karatsuba(b);
      LongInteger result = b.karatsuba(a);
      assertEquals(result.toString(), expectedResult.toString());
   }
   @Test
   public void testKaratsubaPositiveMultiplyPositive(){
      System.out.println("Test: Karatsuba multiplying two positive numbers");
      String valA = CSC338Utils.generateNDigitNumber(5);
      String valB = CSC338Utils.generateNDigitNumber(5);
      LongInteger a = LongInteger.valueOf(valA);
      LongInteger b = LongInteger.valueOf(valB);
	  //System.out.println("a: " + a + " b: " + b);
      LongInteger result = a.karatsuba(b);
      LongInteger expectedResult = a.multiply(b);
	  boolean passedTest = result.equals(expectedResult);
	  if(!passedTest){
		System.out.println("Test failed! result: " + result + " expectedResult: " + expectedResult);
	  }
      assertEquals(passedTest, true);
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
      boolean passedTest = result.equals(expectedResult);
      assertEquals(passedTest, true); 
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
	  boolean passedTest = result.equals(expectedResult);
      assertEquals(passedTest, true);
   }
   
   @Test
   public void testPolynomialCRAWorks(){
      System.out.println("Test: polynomial CRA");
      LongInteger.changeModulus(LongInteger.valueOf(5));

      //LongIntegerPolynomial[] v = new LongIntegerPolynomial[5];
	  LongIntegerPolynomial[] v = new LongIntegerPolynomial[3];
      v[0] = LongIntegerPolynomial.ONE;
      LongInteger[] temp1 = {LongInteger.valueOf(0), LongInteger.valueOf(1)};
      v[1] = new LongIntegerPolynomial(temp1);
      LongInteger[] temp2 = {LongInteger.valueOf(2), LongInteger.valueOf(4), LongInteger.valueOf(-3), LongInteger.valueOf(1)};
      v[2] = new LongIntegerPolynomial(temp2);
      LongInteger[] temp3 = {LongInteger.valueOf(1), LongInteger.valueOf(0), LongInteger.valueOf(-2), LongInteger.valueOf(1)};
      
	  LongIntegerPolynomial[] m = new LongIntegerPolynomial[3];
      LongInteger[] vTemp0 = {LongInteger.valueOf(1), LongInteger.valueOf(1)}; //x+1 
      m[0] = new LongIntegerPolynomial(vTemp0);
      LongInteger[] vTemp1 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(1)}; //x^2+1
      m[1] = new LongIntegerPolynomial(vTemp1);
      LongInteger[] vTemp2 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(1)};//x^4+1
      m[2] = new LongIntegerPolynomial(vTemp2);
      LongInteger[] vTemp3 = {LongInteger.valueOf(1),LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(1)}; //x^5+x+1

      LongIntegerPolynomial result = LongIntegerPolynomial.cra(v, m);
	  //System.out.println("Result: " + result);
      boolean allTrue =true;
      for(int i = 0; i < m.length; i++){
         LongIntegerPolynomial tempPoly = result.remainder(m[i]);
         if(!v[i].equals(tempPoly)){
			System.out.println("i: " + i + " tempPoly: " + tempPoly + " v[i]: " + v[i]);
            allTrue = false;
            break;
         }
      }
      assertEquals(allTrue, true);
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
	  //System.out.println("PolyA degree: " + polyA.degree());
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
   
   @Test
    public void testPolynomialKaratsubaMultiplyByZero(){
        System.out.println("Test: Karatsuba Multiply by Zero");
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
        
        LongIntegerPolynomial result = polyA.karatsuba(LongIntegerPolynomial.ZERO);
        LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
	@Test
    public void testPolynomialKaratsubaMultiplyByOne(){
        System.out.println("Test: Multiply by One");
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
        LongIntegerPolynomial expectedResult = polyA;
        
        LongIntegerPolynomial result = polyA.karatsuba(LongIntegerPolynomial.ONE);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testPolynomialKaratsubaMultiply(){
        System.out.println("Test: Multiply");
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
		
		ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList1.add(LongInteger.ZERO);
			}
			else{
				polyList1.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyB = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = polyA.multiply(polyB);
        
        LongIntegerPolynomial result = polyA.karatsuba(polyB);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testPolynomialKaratsubaMultiplyCommutativeMultiply(){
        System.out.println("Test: Commutative Multiply");
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
		
		ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
		for(int i = 0; i < 15; i++){
			if(i == 3 || i == 5){
			polyList1.add(LongInteger.ZERO);
			}
			else{
				polyList1.add(LongInteger.valueOf(CSC338Utils.generateNDigitNumber(2)));
			}
		}
		LongIntegerPolynomial polyB = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = polyA.karatsuba(polyB);
        
        LongIntegerPolynomial result = polyB.karatsuba(polyA);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
	
   @Test (expected=ParserException.class)
   public void testlagrangeAllZero(){
      System.out.println("Test: Lagrange interpolant of a bunch of 0 points");
	  LongInteger.changeModulus(LongInteger.valueOf(5));
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
   @Test
   public void testInterpolantWorks(){
      System.out.println("Test: polynomial interpolate function works");
      LongInteger.changeModulus(LongInteger.valueOf(17));
      LongInteger[] uPoints = {LongInteger.valueOf(-1), LongInteger.valueOf(0), LongInteger.valueOf(1), LongInteger.valueOf(2), LongInteger.valueOf(6)};
      LongInteger[] vPoints = {LongInteger.valueOf(2), LongInteger.valueOf(5), LongInteger.valueOf(6), LongInteger.valueOf(15), LongInteger.valueOf(4)};
      LongIntegerPolynomial result = LongIntegerPolynomial.interpolate(uPoints, vPoints);

      LongInteger[] answerList = {LongInteger.valueOf(5), LongInteger.valueOf(0),LongInteger.valueOf(2), LongInteger.valueOf(2), LongInteger.valueOf(14)};
      LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
	  
	  boolean passedTest = expectedResult.equals(result);
	  
      assertEquals(passedTest, true);
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




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
import java.util.ArrayList;
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
   public void testKaratsubaMultiplyZero(){
      LongInteger a = LongInteger.valueOf(CSC338Utils.generateNDigitNumber(4));

      LongInteger expectedResult = LongInteger.ZERO;
      LongInteger result = a.karatsuba(LongInteger.ZERO);
      assertEquals(expectedResult, result);
   }
}

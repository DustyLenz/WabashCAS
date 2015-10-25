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
      LongInteger b = LongInteger.
   }
}

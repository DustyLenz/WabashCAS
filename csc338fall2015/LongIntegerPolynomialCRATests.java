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
public class LongIntegerPolynomialCRATests {
   public LongIntegerPolynomialCRATests(){

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
   
   public void testPolynomialCRAWorks(){
      System.out.println("Test: polynomial CRA");
      LongInteger.changeModulus(LongInteger.valueOf(5));

      LongIntegerPolynomial[] v = new LongIntegerPolynomial[5];
      v[0] = LongIntegerPolynomial.ONE;
      LongInteger[] temp1 = {LongInteger.valueOf(0), LongInteger.valueOf(1)};
      v[1] = new LongIntegerPolynomial(temp1);
      LongInteger[] temp2 = {LongInteger.valueOf(2), LongInteger.valueOf(0), LongInteger.valueOf(-3), LongInteger.valueOf(3)};
      v[2] = new LongIntegerPolynomial(temp2);
      LongInteger[] temp3 = {LongInteger.valueOf(1), LongInteger.valueOf(0), LongInteger.valueOf(0), LongInteger.valueOf(0), LongInteger.valueOf(1)};
      v[3] = new LongIntegerPolynomial(temp3);  
      LongInteger[] temp4 = {LongInteger.valueOf(0), LongInteger.valueOf(2), LongInteger.valueOf(0), LongInteger.valueOf(3) };
      v[4] = new LongIntegerPolynomial(temp4); 
   	LongIntegerPolynomial[] m = new LongIntegerPolynomial[5];
      LongInteger[] vTemp0 = {LongInteger.valueOf(1), LongInteger.valueOf(1)};
      m[0] = new LongIntegerPolynomial(vTemp0);
      LongInteger[] vTemp1 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(1)};
      m[1] = new LongIntegerPolynomial(vTemp1);
      LongInteger[] vTemp2 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(1)};
      m[2] = new LongIntegerPolynomial(vTemp2);
      LongInteger[] vTemp3 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(1)};
      m[3] = new LongIntegerPolynomial(vTemp3);
      LongInteger[] vTemp4 = {LongInteger.valueOf(1),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(0),LongInteger.valueOf(1)};
      m[4] = new LongIntegerPolynomial(vTemp4);

      LongIntegerPolynomial result = LongIntegerPolynomial.cra(v, m);
      boolean allTrue =true;
      for(int i = 0; i < m.length; i++){
         LongIntegerPolynomial tempPoly = result.remainder(m[i]);
         if(!v[i].equals(tempPoly)){
            allTrue = false;
            break;
         }
      }
      assertEquals(allTrue, true);
   }
   
}
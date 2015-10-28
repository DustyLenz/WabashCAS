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
   
}
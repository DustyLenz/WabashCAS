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
public class LongIntegerCRATests {
   public LongIntegerCRATests(){

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
}

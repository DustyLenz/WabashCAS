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
public class Project2Tests {
    
    public Project2Tests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testTwoPositiveGCD(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of 2 positive numbers");
        String valA = CSC338Utils.generateNDigitNumber(7);
        String valB = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        LongInteger b = LongInteger.valueOf(valB);
        
        BigInteger exA = new BigInteger(valA);
        BigInteger exB = new BigInteger(valB);
        
        String expectedResult = exA.gcd(exB).abs().toString();
        String result = a.gcd(b).abs().toString();
        
               
        boolean expectedBool = expectedResult.equals(result);
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testTwoNegativeGCD(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of 2 negative numbers");
        String valA = CSC338Utils.generateNDigitNumber(7);
        String valB = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        LongInteger b = LongInteger.valueOf(valB);
        
        a = a.negate();
        b = b.negate();
        
        BigInteger exA = new BigInteger(valA);
        BigInteger exB = new BigInteger(valB);
        
        exA = exA.negate();
        exB = exB.negate();
        
        String expectedResult = exA.gcd(exB).abs().toString();
        String result = a.gcd(b).abs().toString();
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testOnePositiveOneNegativeGCD(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of 1positive, 1 negative number");
        String valA = CSC338Utils.generateNDigitNumber(7);
        String valB = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        LongInteger b = LongInteger.valueOf(valB);
        
        a = a.negate();
        
        BigInteger exA = new BigInteger(valA);
        BigInteger exB = new BigInteger(valB);
        
        exA = exA.negate();
        
        String expectedResult = exA.gcd(exB).abs().toString();
        String result = a.gcd(b).abs().toString();
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testGCDIgnoresOrder(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: Order of GCD doesn't matter");
        String valA = CSC338Utils.generateNDigitNumber(7);
        String valB = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        LongInteger b = LongInteger.valueOf(valB);
        
        String expectedResult = a.gcd(b).toString();
        String result = b.gcd(a).toString();
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testGcdSameVal(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of a val to itself");
        String valA = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        String expectedResult = a.toString();
        
        LongInteger result = a.gcd(a);
        boolean expectedBool = expectedResult.equals(result.toString());
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testGcdwithOne(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of a val to 1");
        String valA = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        String expectedResult = LongInteger.ONE.toString();
        
        LongInteger result = a.gcd(LongInteger.ONE);
        boolean expectedBool = expectedResult.equals(result.toString());
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testGcdwithZero(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: GCD of a val to 0");
        String valA = CSC338Utils.generateNDigitNumber(7);
        LongInteger a = LongInteger.valueOf(valA);
        String expectedResult = LongInteger.ZERO.toString();
        
        LongInteger result = a.gcd(LongInteger.ZERO);
        boolean expectedBool = expectedResult.equals(result.toString());
        
        if(!expectedBool){
            System.out.println("Expected GCD: " + expectedResult  + " Test GCD: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        
        assertEquals(true, expectedBool);
    }
    @Test
    public void testtraditionalEEA(){
        LongInteger.changeModulus(LongInteger.ZERO);
        System.out.println("Test: LongInteger Extended Euclidean Algorithm");
        //BigInteger doesn't have thos, so we will run against mathematica
        //Change this if you want, Dr. Tunrer, in case you're paranoid
        LongInteger a = LongInteger.valueOf("364723");
        LongInteger b = LongInteger.valueOf("438459");
        
        LongInteger[] expectedResult = new LongInteger[3];
        expectedResult[0] = LongInteger.valueOf("1");
        expectedResult[1] = LongInteger.valueOf("172414");
        expectedResult[2] = LongInteger.valueOf("-143419");
        
        LongInteger[] result = a.traditionalEEA(b);
        boolean expectedBool = true;
        int incorrectEntry = -1;
        for(int i = 0; i < expectedResult.length; i++){
            //Check each entry
            if(!expectedResult[i].abs().toString().equals(result[i].abs().toString())){
                expectedBool = false;
                incorrectEntry = i;
            }
            
        }
        
        LongInteger eeaValuesMult = a.multiply(result[1]).add(b.multiply(result[2]));
        //check if ax + by = gcd(a,b)
        if(!eeaValuesMult.abs().equals(a.gcd(b).abs())){
            expectedBool = false;
            incorrectEntry = 4;
        }
        
        if(!expectedBool){
            if(incorrectEntry !=  4){
                System.out.println("Entry: " + incorrectEntry + " has Failed.  Expected Value: " + expectedResult[incorrectEntry] 
                        + " Test value: " + result[incorrectEntry]);
            }
            else{
                System.out.println("EEA does not yield GCD, ax+by = " + eeaValuesMult.toString()
                        + " Should be: " + a.gcd(b).toString());
            }
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    @Test
    public void testDivideMod(){
        System.out.println("Test: LongInteger DivideMod");
        LongInteger.changeModulus(LongInteger.valueOf("337"));
        LongInteger expectedResult = LongInteger.valueOf("126");
        
        LongInteger a = LongInteger.valueOf("458");
        LongInteger b = LongInteger.valueOf("1924");
        
        LongInteger result = a.divide(b);
        
        boolean expectedBool = result.equals(expectedResult);
        
        if(!expectedBool){
            System.out.println("Expected Result: " + expectedResult  + " Test result: " + result);
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    
    @Test
    public void testPolynomialEEA(){
        System.out.println("Test: LongIntegerPolynomial Extended Euclidean Algorithm");
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList0 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList2 = new ArrayList<LongInteger>();
             
        polyList1.add(LongInteger.valueOf(90));
        polyList1.add(LongInteger.valueOf(27));
        polyList1.add(LongInteger.valueOf(86));
        polyList1.add(LongInteger.valueOf(-64));
        polyList1.add(LongInteger.valueOf(-31));
        polyList1.add(LongInteger.valueOf(-87));
        polyList1.add(LongInteger.valueOf(-26));
        polyList1.add(LongInteger.valueOf(4));
        polyList1.add(LongInteger.valueOf(1));
        
        polyList2.add(LongInteger.valueOf(160));
        polyList2.add(LongInteger.valueOf(8));
        polyList2.add(LongInteger.valueOf(12));
        polyList2.add(LongInteger.valueOf(1));
        polyList2.add(LongInteger.valueOf(-161));
        polyList2.add(LongInteger.valueOf(-8));
        polyList2.add(LongInteger.valueOf(-12));
        polyList2.add(LongInteger.valueOf(-1));
        polyList2.add(LongInteger.valueOf(1));
        
        expectedList0.add(LongInteger.valueOf(5));
        expectedList0.add(LongInteger.valueOf(95));
        expectedList0.add(LongInteger.valueOf(6));
        expectedList0.add(LongInteger.valueOf(95));
        expectedList0.add(LongInteger.valueOf(1));
        
        expectedList1.add(LongInteger.valueOf(33));
        expectedList1.add(LongInteger.valueOf(25));
        expectedList1.add(LongInteger.valueOf(9));
        expectedList1.add(LongInteger.valueOf(27));
        
        expectedList2.add(LongInteger.valueOf(73));
        expectedList2.add(LongInteger.valueOf(72));
        expectedList2.add(LongInteger.valueOf(58));
        expectedList2.add(LongInteger.valueOf(74));
        
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        LongIntegerPolynomial expectedResult0 = new LongIntegerPolynomial(expectedList0);
        LongIntegerPolynomial expectedResult1 = new LongIntegerPolynomial(expectedList1);
        LongIntegerPolynomial expectedResult2 = new LongIntegerPolynomial(expectedList2);
        
        LongIntegerPolynomial[] expectedResult = new LongIntegerPolynomial[3];
        expectedResult[0] = expectedResult0;
        expectedResult[1] = expectedResult1;
        expectedResult[2] = expectedResult2;
        
        LongIntegerPolynomial[] result = poly1.traditionalEEA(poly2);
        
        
        boolean expectedBool = true;
        int failedIter = -1;
        for(int i = 0; i < 3; i++){
            if(!result[i].equals(expectedResult[i])){
                failedIter = i;
                expectedBool = false;
                break;
            }
        }
        
        if(!expectedBool){
            System.out.print("Failed on loop: " + failedIter);
            System.out.println(" Expected Result: " + expectedResult[failedIter] + " Test result: " + result[failedIter]);
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    @Test 
    public void testPolynomialGCDWithZero(){
        System.out.println("Test: LongIntegerPolynomial GCD with 0");
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        
        polyList1.add(LongInteger.valueOf(90));
        polyList1.add(LongInteger.valueOf(27));
        polyList1.add(LongInteger.valueOf(86));
        polyList1.add(LongInteger.valueOf(-64));
        polyList1.add(LongInteger.valueOf(-31));
        polyList1.add(LongInteger.valueOf(-87));
        polyList1.add(LongInteger.valueOf(-26));
        polyList1.add(LongInteger.valueOf(4));
        polyList1.add(LongInteger.valueOf(1));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;
        
        LongIntegerPolynomial result = poly1.gcd(LongIntegerPolynomial.ZERO);
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println(" Expected Result: " + expectedResult.toString() + " Test result: " + result.toString());
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    @Test 
    public void testPolynomialGCDWithOne(){
        System.out.println("Test: LongIntegerPolynomial GCD with 1");
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        
        polyList1.add(LongInteger.valueOf(90));
        polyList1.add(LongInteger.valueOf(27));
        polyList1.add(LongInteger.valueOf(86));
        polyList1.add(LongInteger.valueOf(-64));
        polyList1.add(LongInteger.valueOf(-31));
        polyList1.add(LongInteger.valueOf(-87));
        polyList1.add(LongInteger.valueOf(-26));
        polyList1.add(LongInteger.valueOf(4));
        polyList1.add(LongInteger.valueOf(1));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ONE;
        
        LongIntegerPolynomial result = poly1.gcd(LongIntegerPolynomial.ONE);
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println(" Expected Result: " + expectedResult.toString() + " Test result: " + result.toString());
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    @Test 
    public void testPolynomialGCDWithSelf(){
        System.out.println("Test: LongIntegerPolynomial GCD with self");
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        
        polyList1.add(LongInteger.valueOf(90));
        polyList1.add(LongInteger.valueOf(27));
        polyList1.add(LongInteger.valueOf(86));
        polyList1.add(LongInteger.valueOf(-64));
        polyList1.add(LongInteger.valueOf(-31));
        polyList1.add(LongInteger.valueOf(-87));
        polyList1.add(LongInteger.valueOf(-26));
        polyList1.add(LongInteger.valueOf(4));
        polyList1.add(LongInteger.valueOf(1));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial expectedResult = poly1;
        
        LongIntegerPolynomial result = poly1.gcd(poly1);
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println(" Expected Result: " + expectedResult.toString() + " Test result: " + result.toString());
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
    }
    @Test
    public void testdivideModPolynomial(){
        System.out.println("Test: LongIntegerPolynomial DivideMod");
        ArrayList<LongInteger> modList = new ArrayList<LongInteger>();
        modList.add(LongInteger.valueOf(1));
        modList.add(LongInteger.valueOf(0));
        modList.add(LongInteger.valueOf(1));
        LongIntegerPolynomial.changeModulus(new LongIntegerPolynomial(modList));
        LongInteger.changeModulus(LongInteger.valueOf("101"));
        
        
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> expectedList0 = new ArrayList<LongInteger>();
             
        polyList1.add(LongInteger.valueOf(2));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(2));
        polyList1.add(LongInteger.valueOf(4));
        
        polyList2.add(LongInteger.valueOf(-6));
        polyList2.add(LongInteger.valueOf(4));
        polyList2.add(LongInteger.valueOf(0));
        polyList2.add(LongInteger.valueOf(3));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        expectedList0.add(LongInteger.valueOf(29));
        expectedList0.add(LongInteger.valueOf(21));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(expectedList0);
        
        LongIntegerPolynomial result = poly1.divide(poly2);
        
        boolean expectedBool = expectedResult.equals(result);
        
        if(!expectedBool){
            System.out.println(" Expected Result: " + expectedResult.toString() + " Test result: " + result.toString());
        }
        else{
            System.out.println("Test Passed!");
        }
        assertEquals(true, expectedBool);
        
    }
}

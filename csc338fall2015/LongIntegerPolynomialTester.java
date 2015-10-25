/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
/**
 *
 * @author DustyLenz
 */
public class LongIntegerPolynomialTester {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    public void testAddAdditiveIdentity(){
        System.out.println("Test: Additive Identity works");
        ArrayList<LongInteger> polyList = new ArrayList<LongInteger>();
        polyList.add(LongInteger.valueOf(3));
        polyList.add(LongInteger.valueOf(-3));
        polyList.add(LongInteger.valueOf(5));
        LongIntegerPolynomial aPoly = new LongIntegerPolynomial(polyList);
        LongIntegerPolynomial expectedResult = aPoly;
        LongIntegerPolynomial result = aPoly.add(LongIntegerPolynomial.ZERO);
        boolean assertTrue = expectedResult.equals(result);
        assertEquals(true, assertTrue);
    }
    @Test
    public void testAddBiggerToSmaller(){
        System.out.println("Test: Adding bigger to smaller");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(5));
        
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(2));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        answerList.add(LongInteger.valueOf(5));
        answerList.add(LongInteger.valueOf(-1));
        answerList.add(LongInteger.valueOf(5));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        LongIntegerPolynomial result = poly1.add(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testAddSmallertoBigger(){
        System.out.println("Test: Adding smaller to bigger");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(-1));
        polyList2.add(LongInteger.valueOf(5));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        answerList.add(LongInteger.valueOf(5));
        answerList.add(LongInteger.valueOf(-1));
        answerList.add(LongInteger.valueOf(-1));
        answerList.add(LongInteger.valueOf(5));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        
        LongIntegerPolynomial result = poly1.add(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testSubtractSmallertoBigger(){
        System.out.println("Test: subtract bigger from smaller");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(-1));
        polyList2.add(LongInteger.valueOf(5));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        answerList.add(LongInteger.valueOf(1));
        answerList.add(LongInteger.valueOf(-5));
        answerList.add(LongInteger.valueOf(1));
        answerList.add(LongInteger.valueOf(-5));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        
        LongIntegerPolynomial result = poly1.subtract(poly2);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testSubtractScalarMultiply(){
        System.out.println("Test: scalar multiply");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(5));
        polyList1.add(LongInteger.valueOf(-7));
        polyList1.add(LongInteger.valueOf(8));
        
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        answerList.add(LongInteger.valueOf(6));
        answerList.add(LongInteger.valueOf(-6));
        answerList.add(LongInteger.valueOf(10));
        answerList.add(LongInteger.valueOf(-14));
        answerList.add(LongInteger.valueOf(16));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        
        LongIntegerPolynomial result = poly1.multiply(LongInteger.valueOf(2));
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void multiplyByOne(){
        System.out.println("Test: Multiply by One");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial expectedResult = poly1;
        
        LongIntegerPolynomial result = poly1.multiply(LongIntegerPolynomial.ONE);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testMultiply(){
        System.out.println("Test: Multiply");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(-1));
        polyList2.add(LongInteger.valueOf(5));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        answerList.add(LongInteger.valueOf(6));
        answerList.add(LongInteger.valueOf(0));
        answerList.add(LongInteger.valueOf(-1));
        answerList.add(LongInteger.valueOf(26));
        answerList.add(LongInteger.valueOf(-19));
        answerList.add(LongInteger.valueOf(20));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        
        LongIntegerPolynomial result = poly1.multiply(poly2);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void testCommutativeMultiply(){
        System.out.println("Test: Commutative Multiply");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(2));
        polyList2.add(LongInteger.valueOf(-1));
        polyList2.add(LongInteger.valueOf(5));
        
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        answerList.add(LongInteger.valueOf(6));
        answerList.add(LongInteger.valueOf(0));
        answerList.add(LongInteger.valueOf(-1));
        answerList.add(LongInteger.valueOf(26));
        answerList.add(LongInteger.valueOf(-19));
        answerList.add(LongInteger.valueOf(20));
        
        LongIntegerPolynomial expectedResult = poly2.multiply(poly1);
        
        LongIntegerPolynomial result = poly1.multiply(poly2);
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void divideByOne(){
        System.out.println("Test: Divide by One");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        LongIntegerPolynomial expectedResult = poly1;
        
        LongIntegerPolynomial result = poly1.divide(LongIntegerPolynomial.ONE);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void divideByZero(){
        System.out.println("Test: divide by 0");
        expectedEx.expect(ArithmeticException.class);
        expectedEx.expectMessage("Divide by zero");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        LongIntegerPolynomial result = poly1.divide(LongIntegerPolynomial.ZERO);
     }
    @Test
    public void divideByHigherDegree(){
        System.out.println("Test: divide by larger degree");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        polyList2.add(LongInteger.valueOf(3));
        polyList2.add(LongInteger.valueOf(-3));
        polyList2.add(LongInteger.valueOf(4));
        polyList2.add(LongInteger.valueOf(5));
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        LongIntegerPolynomial expectedResult = LongIntegerPolynomial.ZERO;
        LongIntegerPolynomial result = poly1.divide(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void divideByHigherDegreeRemainder(){
        System.out.println("Test: Remainder of divide by highe degree");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(3));
        polyList1.add(LongInteger.valueOf(-3));
        polyList1.add(LongInteger.valueOf(4));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        polyList2.add(LongInteger.valueOf(3));
        polyList2.add(LongInteger.valueOf(-3));
        polyList2.add(LongInteger.valueOf(4));
        polyList2.add(LongInteger.valueOf(5));
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        LongIntegerPolynomial expectedResult = poly1;
        LongIntegerPolynomial result = poly1.remainder(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void divide(){
        System.out.println("Test: divide hard");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(-4));
        polyList1.add(LongInteger.valueOf(1));
        polyList1.add(LongInteger.valueOf(-1));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(1));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        polyList2.add(LongInteger.valueOf(5));
        polyList2.add(LongInteger.valueOf(-2));
        polyList2.add(LongInteger.valueOf(1));
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        answerList.add(LongInteger.valueOf(-2));
        answerList.add(LongInteger.valueOf(2));
        answerList.add(LongInteger.valueOf(1));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        LongIntegerPolynomial result = poly1.divide(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
    @Test
    public void divideRemainder(){
        System.out.println("Test: divide hard check Remainder");
        ArrayList<LongInteger> polyList1 = new ArrayList<LongInteger>();
        polyList1.add(LongInteger.valueOf(-4));
        polyList1.add(LongInteger.valueOf(1));
        polyList1.add(LongInteger.valueOf(-1));
        polyList1.add(LongInteger.valueOf(0));
        polyList1.add(LongInteger.valueOf(1));
        LongIntegerPolynomial poly1 = new LongIntegerPolynomial(polyList1);
        
        ArrayList<LongInteger> polyList2 = new ArrayList<LongInteger>();
        polyList2.add(LongInteger.valueOf(5));
        polyList2.add(LongInteger.valueOf(-2));
        polyList2.add(LongInteger.valueOf(1));
        LongIntegerPolynomial poly2 = new LongIntegerPolynomial(polyList2);
        
        ArrayList<LongInteger> answerList = new ArrayList<LongInteger>();
        answerList.add(LongInteger.valueOf(6));
        answerList.add(LongInteger.valueOf(-13));
        
        LongIntegerPolynomial expectedResult = new LongIntegerPolynomial(answerList);
        LongIntegerPolynomial result = poly1.remainder(poly2);
        
        boolean expectBool = expectedResult.equals(result);
        
        assertEquals(true, expectBool);
    }
}

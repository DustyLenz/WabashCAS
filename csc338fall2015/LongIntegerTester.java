package csc338fall2015;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
public class LongIntegerTester{
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
  @Test
  public void testAddAdditiveIdentity(){
    System.out.println("Test: Additive Identity works");
    int a = (int) CSC338Utils.generateNDigitNumberL(7);
    LongInteger aLong = LongInteger.valueOf(a);
    int result = Integer.valueOf((aLong.add(LongInteger.valueOf(0))).toString());
    assertEquals(a, result);
  }
  @Test
  public void testAddCommutativeProperty(){
    System.out.println("Test: Commutative Property works");
    int a = (int) CSC338Utils.generateNDigitNumberL(7);
    int b = (int) CSC338Utils.generateNDigitNumberL(7);
    LongInteger aLong = LongInteger.valueOf(a);
    LongInteger bLong = LongInteger.valueOf(b);
    int result1 = Integer.valueOf((aLong.add(bLong)).toString());
    int result2 = Integer.valueOf((bLong.add(aLong)).toString());
    assertEquals(result1 , result2);
  }
   @Test
   public void testAddSameLambda() {
      System.out.println("Test: Adding two number of the same lambda");
      int a = (int) CSC338Utils.generateNDigitNumberL(6);
      int b = (int) CSC338Utils.generateNDigitNumberL(6);
      int expectedResult = a+b;
      LongInteger aLong = LongInteger.valueOf(a);
      LongInteger bLong = LongInteger.valueOf(b);
      int result = Integer.valueOf((aLong.add(bLong)).toString());
      assertEquals(result, expectedResult);
   }
   @Test
   public void testAddThisIsBigger() {
      System.out.println("Test: adding smaller number to bigger");
      int a = (int) CSC338Utils.generateNDigitNumberL(6);
      int b = (int) CSC338Utils.generateNDigitNumberL(3);
      int expectedResult = a+b;
      LongInteger aLong = LongInteger.valueOf(a);
      LongInteger bLong = LongInteger.valueOf(b);
      int result = Integer.valueOf((aLong.add(bLong)).toString());
      assertEquals(expectedResult, result);
   }
   @Test
   public void testAddXIsBigger() {
      System.out.println("Test: Adding bigger number to smaller");
      int a = (int) CSC338Utils.generateNDigitNumberL(3);
      int b = (int) CSC338Utils.generateNDigitNumberL(6);
      int expectedResult = a+b;
      LongInteger aLong = LongInteger.valueOf(a);
      LongInteger bLong = LongInteger.valueOf(b);
      int result = Integer.valueOf((aLong.add(bLong)).toString());
      assertEquals(expectedResult, result);
   }
    @Test
   public void testAddCarry() {
      System.out.println("Test: Carry digits");
      int a = 9999;
      int b = 1;
      int expectedResult = a+b;
      LongInteger aLong = LongInteger.valueOf(a);
      LongInteger bLong = LongInteger.valueOf(b);
      int result = Integer.valueOf((aLong.add(bLong)).toString());
      assertEquals(expectedResult, result);
   }
    @Test
   public void testAddingNegatives() {
      System.out.println("Test: adding Two Negative Numbers");
      int a = -(int) CSC338Utils.generateNDigitNumberL(3);
      int b = -(int) CSC338Utils.generateNDigitNumberL(6);
      int expectedResult = a+b;
      LongInteger aLong = LongInteger.valueOf(a);
      LongInteger bLong = LongInteger.valueOf(b);
      int result = Integer.valueOf((aLong.add(bLong)).toString());
      assertEquals(expectedResult, result);
   }
    
    @Test
    public void subtractAdditiveIdentity(){
        System.out.println("Test : Subtracting identity");
        int expectedResult = 15;
        LongInteger aLong = LongInteger.valueOf(expectedResult);
        int result = Integer.valueOf(aLong.add(LongInteger.ZERO).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void subtractSameLambda(){
        System.out.println("Test: Same Lambda Subtraction");
        int a = (int) CSC338Utils.generateNDigitNumberL(4);
        int b = (int) CSC338Utils.generateNDigitNumberL(4);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a-b;
        int result = Integer.valueOf(aLong.subtract(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void subtractionBiggerFromSmaller(){
        System.out.println("Test: Subtracting Bigger number from Smaller");
        int a = (int) CSC338Utils.generateNDigitNumberL(4);
        int b = (int) CSC338Utils.generateNDigitNumberL(6);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a-b;
        int result = Integer.valueOf(aLong.subtract(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void subtractionSmallerfromBigger(){
        System.out.println("Test: Subtracting Smaller number from Bigger");
        int a = (int) CSC338Utils.generateNDigitNumberL(6);
        int b = (int) CSC338Utils.generateNDigitNumberL(4);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a-b;
        int result = Integer.valueOf(aLong.subtract(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void subtractNegativeNumbers(){
        System.out.println("Test: Subtracting negative numbers");
        int a = -(int) CSC338Utils.generateNDigitNumberL(6);
        int b = -(int) CSC338Utils.generateNDigitNumberL(4);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a-b;
        int result = Integer.valueOf(aLong.subtract(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void subtractTestCarry(){
        System.out.println("Test: testing carry in Subtracting");
        int a = 1000;
        int b = 1;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a-b;
        int result = Integer.valueOf(aLong.subtract(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyByZero(){
        System.out.println("Test: Multiply by Zero");
        int a = (int) CSC338Utils.generateNDigitNumberL(6);
        int b = 0;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyIdentity(){
        System.out.println("Test:multiplicative identity");
        int a = (int) CSC338Utils.generateNDigitNumberL(6);
        int b = 1;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyCommutative(){
        System.out.println("Test: teting multiplicative commutative property");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = (int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = Integer.valueOf(bLong.multiply(aLong).toString());
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyBiggerbySmaller(){
        System.out.println("Test: multiply bigger by smaller");
        int a = (int) CSC338Utils.generateNDigitNumberL(5);
        int b = (int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplySmallerbyBigger(){
        System.out.println("Test: multiply smaller by bigger");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = (int) CSC338Utils.generateNDigitNumberL(5);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyNegbyPos(){
        System.out.println("Test: multiply negative by positive number");
        int a = -(int) CSC338Utils.generateNDigitNumberL(3);
        int b = (int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyPosbyNeg(){
        System.out.println("Test: multiply positive by negative number");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = -(int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiplyNegbyNeg(){
        System.out.println("Test: multiply negative by negative");
        int a = -(int) CSC338Utils.generateNDigitNumberL(3);
        int b = -(int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a*b;
        int result = Integer.valueOf(aLong.multiply(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideByOne(){
        System.out.println("Test: divide by 1");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = 1;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideByZero(){
        System.out.println("Test: divide by 0");
        expectedEx.expect(ArithmeticException.class);
        expectedEx.expectMessage("Divide by zero");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = 0;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        aLong.divide(bLong);
    }
    @Test
    public void divideBiggerBySmaller(){
        System.out.println("Test: Divide bigger number by smaller");
        int a = (int) CSC338Utils.generateNDigitNumberL(6);
        int b = (int) CSC338Utils.generateNDigitNumberL(3);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideSmallerbyBigger(){
        System.out.println("Test: Divide smaller number by bigger");
        int a = (int) CSC338Utils.generateNDigitNumberL(3);
        int b = (int) CSC338Utils.generateNDigitNumberL(6);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideCheckRemainder(){
        System.out.println("Test: Remainder of division");
        int a = 12398;
        int b = 5;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a%b;
        int result = Integer.valueOf(aLong.remainder(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideCheckNegativeRemainder(){
        System.out.println("Test: Negative Remainder of division");
        int a = -12398;
        int b = 5;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a%b;
        int result = Integer.valueOf(aLong.remainder(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideCheckZeroRemainder(){
        System.out.println("Test: No remainder of division");
        int a = 12395;
        int b = 5;
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a%b;
        int result = Integer.valueOf(aLong.remainder(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideNegbyPos(){
        System.out.println("Test: Divide negative by positive");
        int a = -(int) CSC338Utils.generateNDigitNumberL(6);
        int b = (int) CSC338Utils.generateNDigitNumberL(2);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void dividePosbyNeg(){
        System.out.println("Test: Divide positive by negative");
        int a = (int) CSC338Utils.generateNDigitNumberL(6);
        int b = -(int) CSC338Utils.generateNDigitNumberL(2);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideNegbyNeg(){
        System.out.println("Test: Divide negative by negative");
        int a = -(int) CSC338Utils.generateNDigitNumberL(6);
        int b = -(int) CSC338Utils.generateNDigitNumberL(2);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a/b;
        int result = Integer.valueOf(aLong.divide(bLong).toString());
        assertEquals(expectedResult, result);
    }
    @Test
    public void divideNegRemainder(){
        System.out.println("Test: divide negative remainder");
        int a = -(int) CSC338Utils.generateNDigitNumberL(6);
        int b = (int) CSC338Utils.generateNDigitNumberL(2);
        LongInteger aLong = LongInteger.valueOf(a);
        LongInteger bLong = LongInteger.valueOf(b);
        int expectedResult = a%b;
        int result = Integer.valueOf(aLong.remainder(bLong).toString());
        assertEquals(expectedResult, result);
    }
}
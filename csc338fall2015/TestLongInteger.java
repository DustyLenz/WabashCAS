/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author turnerw
 */
public class TestLongInteger {

 public static void testValueOf() {
  LongInteger li;

  for( int k = -20; k < 50; ++k ) {
   li = LongInteger.valueOf( k );
   System.out.println("k = " + k + ", pos = " + li.positive() + ", digits = " + li.digits() + ", li = " + li.toString() );
  }
 }

 public static void testConvertBase() {
  Integer fromRadix = new Integer( 7 );
  Integer toRadix = new Integer( 10 );
  ArrayList<Integer> oldDigits = new ArrayList<Integer>();
//  for( int i = 0; i < 10; ++i ) {
//   oldDigits.add( new Integer(i) );
//  }
//  Collections.reverse(oldDigits );

  oldDigits.add( new Integer(0) );
  oldDigits.add( new Integer(1) );
  oldDigits.add( new Integer(1) );

  System.out.println( "oldDigits = " + oldDigits.toString() );

  List<Integer> newDigits = LongInteger.convertBase(oldDigits, fromRadix, toRadix);

  System.out.println( "newDigits = " + newDigits.toString() );

 }

 public static void testNegate() {

  System.out.println( "*** Called testNegate ***" );

  LongInteger x = LongInteger.valueOf(56300);
  LongInteger y = x.negate();

  System.out.println( "x = " + x.digits().toString() + ", y = " + y.digits().toString() + ", radix = " + LongInteger.radix() );
  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", radix = " + LongInteger.radix() );
 }

 public static void testAdd() {

  System.out.println( "*** Called testAdd ***" );

  LongInteger x = LongInteger.valueOf( 54321 );
  LongInteger y = LongInteger.valueOf( 98765 );
  LongInteger z = x.add(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.add(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( 98765 );
  z = x.add(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

  x = LongInteger.valueOf( 54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.add(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

 }

 public static void testSubtract() {

  System.out.println( "*** Called testSubtract ***" );

  LongInteger x = LongInteger.valueOf( 54321 );
  LongInteger y = LongInteger.valueOf( 98765 );
  LongInteger z = x.subtract(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.subtract(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( 98765 );
  z = x.subtract(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

  x = LongInteger.valueOf( 54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.subtract(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

  x = LongInteger.valueOf( 40 );
  y = LongInteger.valueOf( 30 );
  z = x.subtract(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

 }

 public static void testMultiply() {

  System.out.println( "*** Called testMultiply ***" );

  LongInteger x = LongInteger.valueOf( 12345 );
  LongInteger y = LongInteger.valueOf( 98765 );
  LongInteger z = x.multiply(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.multiply(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );

  x = LongInteger.valueOf( -54321 );
  y = LongInteger.valueOf( 98765 );
  z = x.multiply(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

  x = LongInteger.valueOf( 54321 );
  y = LongInteger.valueOf( -98765 );
  z = x.multiply(y);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", z = " + z.toString() );
  System.out.println( "z.digits() = " + z.digits().toString() );

 }

 public static void testCompare() {

  System.out.println( "*** Called testMultiply ***" );

  LongInteger x = LongInteger.valueOf( 5 );
  LongInteger y = LongInteger.valueOf( -3 );
  int xcomp = x.compare(y);
  int ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );

  x = LongInteger.valueOf( 53 );
  y = LongInteger.valueOf( 4 );
  xcomp = x.compare(y);
  ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );

  x = LongInteger.valueOf( 53 );
  y = LongInteger.valueOf( 42 );
  xcomp = x.compare(y);
  ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );

  x = LongInteger.valueOf( 53 );
  y = LongInteger.valueOf( 52 );
  xcomp = x.compare(y);
  ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );

  x = LongInteger.valueOf( -53 );
  y = LongInteger.valueOf( -52 );
  xcomp = x.compare(y);
  ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );

  x = LongInteger.valueOf( 53 );
  y = LongInteger.valueOf( 53 );
  xcomp = x.compare(y);
  ycomp = y.compare(x);

  System.out.println( "x = " + x.toString() + ", y = " + y.toString() + ", xcomp = " + xcomp + ", ycomp = " + ycomp );


 }

 public static void testDivide() {

  System.out.println( "*** Called testDivide ***" );

  LongInteger x = LongInteger.valueOf( 40 );
  LongInteger y = LongInteger.valueOf( 3 );
  LongInteger[] z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( 600 );
  y = LongInteger.valueOf( 39 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( 99999 );
  y = LongInteger.valueOf( 10 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( 35 );
  y = LongInteger.valueOf( 6 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );
//  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.digits().toString(), y.digits().toString(), z[0].digits().toString(), z[1].digits().toString() );

  x = LongInteger.valueOf( -35 );
  y = LongInteger.valueOf( -6 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( -35 );
  y = LongInteger.valueOf( 6 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( 35 );
  y = LongInteger.valueOf( -6 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

  x = LongInteger.valueOf( -1 );
  y = LongInteger.valueOf( 1 );
  z = x.divideAndRemainder(y);

  System.out.printf( "x = %s, y = %s, q = %s, r = %s\n", x.toString(), y.toString(), z[0].toString(), z[1].toString() );

 }

 public static void testStringConstructor() {
  System.out.println( "*** Called testStringConstructor ***");

  LongInteger x;
  String str;

  str = "-123456789";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = "   -    123456789";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = "  +   123456789";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = "-123abc";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = "-123.5";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = " 000 ";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );

  str = "   ";
  x = new LongInteger(str);
  System.out.printf("str = %s, x = %s, x.digits = %s\n", str, x.toString(), x.digits().toString() );
 }

 public static void testModular() {
  System.out.println( "*** Called testModular ***");

//  LongInteger.modulus( LongInteger.valueOf( 101 ) );

  LongInteger x = LongInteger.valueOf( 99899 );
  LongInteger y = LongInteger.valueOf( 150 );
  LongInteger z;

  z = x.add(y);
  System.out.printf("%s + %s = %s\n", x.toString(), y.toString(), z.toString() );

  z = x.subtract(y);
  System.out.printf("%s - %s = %s\n", x.toString(), y.toString(), z.toString() );

  z = x.multiply(y);
  System.out.printf("%s * %s = %s\n", x.toString(), y.toString(), z.toString() );

  z = x.divide(y);
  System.out.printf("%s / %s = %s\n", x.toString(), y.toString(), z.toString() );

  z = x.remainder(y);
  System.out.printf("%s rem %s = %s\n", x.toString(), y.toString(), z.toString() );


 }

public static void testEEA() {
  System.out.println( "*** Called testEEA ***");

  LongInteger.modulus( LongInteger.valueOf( 47 ) );
  
  LongInteger x = LongInteger.valueOf( 47 );
  LongInteger y = LongInteger.valueOf( 100 );
  LongInteger[] z;
  boolean pass;

  System.out.println( "Using modular arithmetic? " + LongInteger.modular() );
  System.out.println( "Modulus congruent to zero? " + LongInteger.modulus().equals( LongInteger.ZERO ) );
  System.out.println( "Modulus - 0 = " + LongInteger.modulus().subtract( LongInteger.ZERO ) );
  
  z = x.traditionalEEA(y);
  pass = z[0].equals( z[1].multiply(x).add( z[2].multiply(y) ) );
  System.out.printf("Traditional: %s: %s = %s * %s + %s * %s\n", pass, z[0], z[1], x, z[2], y );

  z = x.EEA(y);
  pass = z[0].equals( z[1].multiply(x).add( z[2].multiply(y) ) );
  System.out.printf("Nontraditional: %s: %s = %s * %s + %s * %s\n", pass, z[0], z[1], x, z[2], y );


 }

public static void testInverse() {
  System.out.println( "*** Called testInverse ***");

  LongInteger.modulus( LongInteger.valueOf( 47 ) );
  
  LongInteger x = LongInteger.valueOf( 100 );
//  LongInteger y = LongInteger.valueOf( 100 );
  LongInteger z;
  boolean pass;

  z = x.inverse();
  pass = z.multiply(x).equals( LongInteger.ONE );
  System.out.printf("Inverse: %s: %s * %s = %s\n", pass, z, x, LongInteger.ONE );

 }

public static void testPower() {
  System.out.println( "*** Called testPower ***");

  LongInteger.modulus( LongInteger.valueOf( 101 ) );
  
  LongInteger x = LongInteger.valueOf( 2 );
  LongInteger y = LongInteger.valueOf( -1 );
  LongInteger z;
  boolean pass;

  z = x.pow( y );
  System.out.printf("%s^%s = %s\n", x, y, z);

 }

 public static void main( String[] args ) {
//  TestLongInteger.testValueOf();
//  TestLongInteger.testConvertBase();
//  TestLongInteger.testNegate();
//  TestLongInteger.testAdd();
//  TestLongInteger.testSubtract();
//  TestLongInteger.testMultiply();
//  TestLongInteger.testCompare();
//  TestLongInteger.testDivide();
//  TestLongInteger.testStringConstructor();
//  TestLongInteger.testModular();
//  TestLongInteger.testEEA();
//  TestLongInteger.testInverse();
//  TestLongInteger.testPower();
  
//	System.out.println( LongInteger.modulus().toString() );
//	System.out.println( LongIntegerPolynomial.modulus().toString() );
//	
//	LongInteger[] coeffs = { LongInteger.ONE, LongInteger.ZERO, LongInteger.ONE };
//	LongIntegerPolynomial f = new LongIntegerPolynomial( coeffs );
//	System.out.println( f.toString() );
//	
//	LongIntegerPolynomial.modulus( f );
//	System.out.println( LongIntegerPolynomial.modulus().toString() );
	
	 LongInteger a = LongInteger.valueOf( 5);
	 LongInteger.modulus( LongInteger.valueOf( 11 ));
	 
	 System.out.printf( "-a = %s\n", a.negate() );

 }

}

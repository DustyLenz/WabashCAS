/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/** Multiprecision integer class
 *
 * @author turnerw
 */
public class LongInteger {

   // Declare static members first so can use later
   private final static Integer radix = 10;
   public static LongInteger ZERO = LongInteger.valueOf(0);
   public static LongInteger ONE = LongInteger.valueOf(1);
   public static LongInteger TEN = LongInteger.valueOf(10);
   private static LongInteger modulus = LongInteger.ZERO;
   //private static LongInteger modulus = LongInteger.valueOf(5);

   // Non-static members
   private ArrayList<Integer> digits;
   private boolean positive;

   /** Default constructor.
    *
    */
   public LongInteger() {
      this.digits = new ArrayList<Integer>();
      this.positive = true;
   }

   /** Copy constructor
    *
    * @param n LongInteger to copy
    */
   public LongInteger(LongInteger n) {
      this.digits = new ArrayList<Integer>(n.digits);
      this.positive = n.positive;

      // Minimize length of result
      this.minimizeLength();

   }

   /** Constructor from list of digits and a the sign.
    *
    * @param digits List of digits
    * @param positive Is it positive?
    */
   public LongInteger(List<Integer> digits, boolean positive) {
      this.digits = new ArrayList<Integer>(digits);
      this.positive = positive;

      // Minimize length of result
      this.minimizeLength();
   }

   /** Construct a positive integer from a list of digits.
    *
    * @param digits List of digits
    */
   public LongInteger(List<Integer> digits) {
      this(digits, true);
   }

   /** Constructor from array of digits and a the sign.
    *
    * @param digits Array of digits
    * @param positive Is it positive?
    */
   public LongInteger(Integer[] digits, boolean positive) {
      this.positive = positive;
      this.digits = new ArrayList<Integer>();
      for (int i = 0; i < digits.length; ++i) {
         this.digits.add(digits[i]);
      }

      // Minimize length of result
      this.minimizeLength();
   }

   /** Construct a positive integer from a array of digits.
    *
    * @param digits Array of digits
    */
   public LongInteger(Integer[] digits) {
      this(digits, true);
   }

   /** Constructor from array of digits and a the sign.
    *
    * @param digits Array of digits
    * @param positive Is it positive?
    */
   public LongInteger(int[] digits, boolean positive) {
      this.positive = positive;
      this.digits = new ArrayList<Integer>();
      for (int i = 0; i < digits.length; ++i) {
         this.digits.add(new Integer(digits[i]));
      }

      // Minimize length of result
      this.minimizeLength();
   }

   /** Construct a positive integer from a array of digits.
    *
    * @param digits Array of digits
    */
   public LongInteger(int[] digits) {
      this(digits, true);
   }

   /** Construct a LongInteger from a string.
    * This will take a value based on the string before the first nondigit
    * (with the exception of an initial + or -).
    *
    * @param str
    */
   public LongInteger(String str) {
      this.digits = new ArrayList<Integer>();
      this.positive = true;

      // Iterate through position in the string
      int pos = 0;
      char chr = ' ';

      // Skip initial whitespace
      while( pos < str.length() && Character.isWhitespace( chr = str.charAt( pos ) ) )
         ++pos;

      // All whitespace?
      if( pos == str.length() ) {
         System.out.println( "At end!" );
         return;
      }

      // Stopped at sign?
      if( chr == '-' || chr == '+' ) {
         this.positive = ( chr == '+' );

         ++pos; // Increment position

         // Skip any more whitespace
         while( pos < str.length() && Character.isWhitespace( chr = str.charAt( pos ) ) )
            ++pos;
      }

      // Look now only at (decimal) digits
      while( pos < str.length() && Character.isDigit( chr = str.charAt( pos ) ) ) {
         this.digits.add( Character.getNumericValue( chr ) );
         ++pos;
      }

      // Now done with all digits, so reverse and convert base
      Collections.reverse( this.digits );
      this.digits = LongInteger.convertBase( this.digits , 10, radix);
      this.minimizeLength();
   }

   /** Create a LongInteger from a string.
    *
    * @param str
    * @return
    */
   public static LongInteger valueOf( String str ) {
      return new LongInteger( str );
   }

   /** Create a LongInteger from a value.
    *
    * @param val
    * @return
    */
   public static LongInteger valueOf(int val) {
      LongInteger result = new LongInteger();

      // Negative?
      if (val < 0) {
         result.positive = false;
         val *= -1;
      }

      // Russian peasant method to find digits
      Integer quo = new Integer(val);
      Integer rem = new Integer(0);

      while (quo > 0) {
         result.digits.add(quo % LongInteger.radix);
         quo /= LongInteger.radix;
      }

      // Minimize length of result
      result.minimizeLength();

      //  // Modular arithmetic?
      //  if( LongInteger.modulus.compare( LongInteger.ZERO ) > 0 )
      //   result = result.remainder( LongInteger.modulus );

      // Done
      return result;
   }

   public static Integer radix() { return LongInteger.radix; }

   public static LongInteger modulus() { return LongInteger.modulus; }

   public static LongInteger changeModulus(LongInteger x) {
      modulus = x;
      return x;
   }

   public static LongInteger modulus( LongInteger mod ) {
      return LongInteger.modulus = new LongInteger( mod );
   }

   /** Using modular arithmetic?
    * Checks without using the compare or equals
    * 
    * @return 
    */
   public static boolean modular() {
      return !LongInteger.modulus.digits.isEmpty();
   }

   /** Reduce modulo the static modulus
    * This method DOES NOT change this!
    * 
    * @return 
    */
   public LongInteger modularReduction() {
      // Test if modulus zero, but do not use the compare method to avoid recursion
      if( LongInteger.modulus.digits.isEmpty() ) // ZERO, so no modulus
         return this;
      else // Modular arithmetic
         return this.remainder( LongInteger.modulus );
   }

   /** Minimizes the size of the digit list so the last (mist significant)
    * digits is nonzero.
    */
   private void minimizeLength() {
      // Remove leading zero digits
      int i = this.digits.size();
      while (i > 0 && 0 == this.digits.get(--i)) {
         this.digits.remove(i);
      }

      // Zero always positive
      if( this.digits.isEmpty() )
         this.positive = true;

   }

   /** Access list of digits
    *
    * @return
    */
   public List<Integer> digits() {
      return this.digits;
   }

   /** Is this positive?
    *
    * @return
    */
   public boolean positive() {
      return this.positive;
   }

   public int length() {
      this.minimizeLength();
      return this.digits.size();
   }

   /** Compare two LongIntegers
    * Returns 1, 0, or -1 if this is greater than, equal to, or less than x, respectively.
    * 
    * This method ignores modular arithmetic and compares the actual representations
    * as integers.
    * 
    * @param x
    * @return 
    */
   public int compare(LongInteger x) {
      // Ensure corrent form (minimal length)
      this.minimizeLength();
      x.minimizeLength();

      // Quick sign test first
      if (this.positive != x.positive) {
         //   System.out.println("Different sign" + this.positive );
         if (this.positive) {
            return 1;
         } else {
            return -1;
         }
      }

      // Same sign, so compare digits

      // Make flag for ease in return statements
      int greater = 1;
      if (!this.positive) {
         greater = -1;
      }
      //  System.out.println("greater = " + greater);

      // Compare lengths
      //  System.out.printf("this.length = %d, x.length = %d\n", this.length(), x.length() );
      if (this.length() > x.length()) {
         return greater;
      } else if (this.length() < x.length()) {
         return -greater;
      }

      //  System.out.println( "Same length");

      // Same length, so must compare individual digits, starting with most significant
      int length = this.length();
      Integer thisDigit;
      Integer xDigit;
      for (int i = length - 1; i >= 0; --i) {
         thisDigit = this.digits.get(i);
         xDigit = x.digits.get(i);
         //   System.out.printf("i = %d, thisDigits = %s, xDigit = %s\n", i, thisDigit.toString(), xDigit.toString() );
         if (thisDigit > xDigit) {
            return greater;
         } else if (thisDigit < xDigit) {
            return -greater;
         }
      }

      // All digits equal
      return 0;
   }

   /** Checks if two LongIntegers are equal.
    * This method uses the modular arithmetic, and it returns true whenever 
    * the two LongIntegers are congruent modulo the modulus.  Use compare
    * if you want to compare their actual integer representations.
    * 
    * @param x
    * @return whether the two LongIntegers are congruent
    */
   public boolean equals(LongInteger x) {
      //  System.out.printf( "equals: %s: %s == %s; %s\n", 0 == this.compare(x), this, x, this.subtract( x ).digits );
      if( LongInteger.modular() )
         return 0 == this.subtract( x ).compare( LongInteger.ZERO );
      else
         return 0 == this.compare(x);
   }

   /** Add a LongInteger with the same sign.
    *
    * This method ignores the modulus and always computes in the integers.
    *
    * @param x LongInteger with the same sign as this
    * @return
    */
   private LongInteger addSameSign(LongInteger x) {
      // Create new LongInteger for the result
      LongInteger result = new LongInteger();

      // Set sign
      result.positive = this.positive;

      // Find smaller length
      int smallerLength = (this.digits.size() < x.digits.size()) ? this.digits.size() : x.digits.size();
      //  System.out.println( "smallerLength = " + smallerLength );

      // Add terms
      Integer carry = Integer.valueOf(0);
      Integer newDigit;
      for (int i = 0; i < smallerLength; ++i) {
         newDigit = this.digits.get(i) + x.digits.get(i) + carry;

         // Check for overflow and carry
         if (newDigit < LongInteger.radix) {
            carry = Integer.valueOf(0);
         } else {
            newDigit -= LongInteger.radix;
            carry = Integer.valueOf(1);
         }

         result.digits.add(newDigit);
      }

      // Find remaining digits to use after smaller exhausted
      List< Integer> rest = null;
      if (this.digits.size() > smallerLength) {
         rest = this.digits.subList(smallerLength, this.digits.size());
      } else {
         rest = x.digits.subList(smallerLength, x.digits.size());
      }

      // Add the remaining digits, using the carry
      ListIterator<Integer> itr = rest.listIterator();
      while (itr.hasNext()) {
         newDigit = itr.next() + carry;

         // Again check for overflow and carry
         if (newDigit < LongInteger.radix) {
            carry = Integer.valueOf(0);
         } else {
            newDigit -= LongInteger.radix;
            carry = Integer.valueOf(1);
         }

         result.digits.add(newDigit);
      }

      // Append carry if necessary
      if (carry != 0) {
         result.digits.add(carry);
      }

      // Done
      return result;
   }

   /** Subtract a LongInteger with the same sign.
    *
    * If the leading digit in the result is negative, we still need to reverse the rest.
    *
    * This method ignores the modulus and always computes in the integers.
    *
    * @param x LongInteger with the same sign as this
    * @return
    */
   private LongInteger subtractSameSign(LongInteger x) {

      //  System.out.println( "Called subtractSameSign");

      // Create new LongInteger for the result
      LongInteger result = new LongInteger();

      // Set sign for now.
      result.positive = this.positive;

      // Find smaller length
      int smallerLength = (this.digits.size() < x.digits.size()) ? this.digits.size() : x.digits.size();
      //  System.out.println( "smallerLength = " + smallerLength );

      // Add terms
      Integer carry = Integer.valueOf(0);
      Integer newDigit;
      for (int i = 0; i < smallerLength; ++i) {
         newDigit = this.digits.get(i) - x.digits.get(i) + carry;

         // Because mixed sign, we will have underflor rather than overflow
         if (newDigit < 0) {
            newDigit += LongInteger.radix;
            carry = Integer.valueOf(-1);
         } else {
            carry = Integer.valueOf(0);
         }

         result.digits.add(newDigit);
         //   System.out.printf("i = %d, digits = %s, carry = %s\n", i, result.digits, carry );
      }

      // Find remaining digits to use after smaller exhausted
      List< Integer> rest = null;
      if (this.digits.size() > smallerLength) {
         rest = this.digits.subList(smallerLength, this.digits.size());
      } else {
         rest = new ArrayList<Integer>();
         // Reverse the signs of x
         for (int i = smallerLength; i < x.digits.size(); ++i) {
            rest.add(-x.digits.get(i));
         }
      }

      // Add the remaining digits, using the carry
      ListIterator<Integer> itr = rest.listIterator();
      while (itr.hasNext()) {
         newDigit = itr.next() + carry;

         // Again, be aware of underflow
         if (newDigit < 0) {
            newDigit += LongInteger.radix;
            carry = Integer.valueOf(-1);
         } else {
            carry = Integer.valueOf(0);
         }

         result.digits.add(newDigit);
      }

      //  System.out.printf("digits = %s\n", result.digits );

      // If carry not zero, it will be negative, so need to reverse via radix-complement
      if (carry != 0) {
         result = result.radixComplement();
      }

      //  System.out.printf("digits = %s\n", result.digits );


      // Done
      result.minimizeLength();
      return result;
   }

   /** Computer the radix complement of this LongIntger.
    * This method is used in the subtractSameSign to change the sign of the difference.
    *
    * @return
    */
   private LongInteger radixComplement() {
      // Create storage for the result
      LongInteger result = new LongInteger();

      // Reverse the sign.
      result.positive = !this.positive;

      // Starting with least significant digit, do the conversion
      ListIterator<Integer> itr = this.digits.listIterator();
      Integer newDigit = Integer.valueOf(0);

      // Put trailing zeros on the result
      while (itr.hasNext() && 0 == (newDigit = itr.next())) {
         result.digits.add(newDigit);
      }

      // If not all zero, now at first nonzero digit.
      if (newDigit != 0) {
         result.digits.add(LongInteger.radix - newDigit);
      }

      // Now convert rest of digits for result
      Integer radixMinusOne = LongInteger.radix - 1;
      while (itr.hasNext()) {
         result.digits.add(radixMinusOne - itr.next());
      }

      // Done
      return result;

   }

   /** Addition
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   public LongInteger add(LongInteger x) {
      // Decide which helper function to use
      LongInteger result;
      if (this.positive == x.positive) {
         result = this.addSameSign(x);
      } else {
         result = this.subtractSameSign(x);
      }

      // Modular arithmetic?
      if( LongInteger.modular() )
         result = result.remainder( LongInteger.modulus );

      // Done
      return result;
   }

   /** Subtraction
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   public LongInteger subtract(LongInteger x) {
      return this.add( x.changeSign() );
   }

   /** "Scalar" multiplication.
    * This method is written in parallel to that of a polynomial to aid in the
    * public methods.
    *
    * @param x  Integer "scalar" by which to multiply
    * @return
    */
   private LongInteger multiply(Integer x) {
      // Create result
      LongInteger result = new LongInteger();

      // Find correct sign and ensure positive scalar
      Integer scalar = null;
      if (x < 0) {
         result.positive = !this.positive;
         scalar = -x;
      } else {
         result.positive = this.positive;
         scalar = x;
      }

      // Iterate over digits, multiplying and carrying
      ListIterator<Integer> itr = this.digits.listIterator();
      Integer carry = Integer.valueOf(0);
      Integer newDigit;
      while (itr.hasNext()) {
         newDigit = itr.next() * scalar + carry;

         // Reduce to allowed size
         if (newDigit >= LongInteger.radix) {
            carry = newDigit / LongInteger.radix;
            newDigit = newDigit % LongInteger.radix;
         } else {
            carry = Integer.valueOf(0);
         }

         result.digits.add(newDigit);
      }

      // Append carry if necessary
      if (carry != 0) {
         result.digits.add(carry);
      }

      // Modular arithmetic?
      //  if( LongInteger.modulus.compare( LongInteger.ZERO ) > 0 )
      //   result = result.remainder( LongInteger.modulus );

      // Done
      return result;
   }

   /** Multiplication
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   public LongInteger multiply(LongInteger x) {
      // Use number of digits of x to make decisions
      int length = x.digits.size();

      if (length == 0) // Zero integer
      {
         return LongInteger.ZERO;
      }

      /* Multiplying by a real LongInteger.  Use the classical algorithm via
       * a Horner-like rule.  Multiply all as positive to begin
       */
      LongInteger abs = this.abs();
      LongInteger result = abs.multiply(x.digits.get(length - 1));

      for (int i = length - 2; i >= 0; --i) {
         result.digits.add(0, Integer.valueOf(0)); // Multiply by x
         result = result.add(abs.multiply(x.digits.get(i)));
      }

      // Modular arithmetic already taken care of in the above additions

      // Set sign
      result.positive = this.positive == x.positive;

      // Modular arithmetic already taken care of in the above additions
      // Modular arithmetic?
      if( LongInteger.modulus.compare( LongInteger.ZERO ) > 0 )
         result = result.remainder( LongInteger.modulus );

      // Done
      return result;
   }

   private Integer leadingDigit() {
      this.minimizeLength();

      if (this.digits.isEmpty()) {
         return Integer.valueOf(0);
      } else {
         return this.digits.get(this.digits.size() - 1);
      }
   }

   /** Multiply this by a power of the radix
    *
    * @param power
    * @return
    */
   private LongInteger shift(int power) {
      // Create empty result
      LongInteger result = new LongInteger();

      // Add zeroes for the shift
      for (int k = 0; k < power; ++k) {
         result.digits.add(Integer.valueOf(0));
      }

      // Now add all the coeffcients of this
      result.digits.addAll(this.digits);

      // Done
      return result;
   }

   /** Division
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   public LongInteger divide(LongInteger x) {
      if( LongInteger.modulus.compare( LongInteger.ZERO ) == 0 )
         return this.divideAndRemainder(x)[0];
      else
         return this.divideMod(x);
   }

   /** Remainder after division
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   public LongInteger remainder(LongInteger x) {
      return this.divideAndRemainder(x)[1];
   }

   /** Division returning both quotient and remainder
    * Does not change this.
    *
    * This method ignores the modulus and always computes in the integers.
    *
    * @param b Other operand
    * @return Array with quotient first and then remainder
    */
   public LongInteger[] divideAndRemainder(LongInteger x) {
      // Divide assuming both positive
      LongInteger[] result = this.divideAndRemainderAbsolute(x);

      // Fix for negative signs
      if (!this.positive && !result[1].equals( LongInteger.ZERO ) ) {
         result[0] = result[0].addSameSign(LongInteger.ONE);
         result[1] = x.abs().subtract(result[1]);
      }

      if (this.positive != x.positive) {
         result[0] = result[0].changeSign();
      }

      return result;

   }

   /** Division absolute values returning both quotient and remainder
    * Does not change this.
    * 
    * This method ignores the modulus and always computes in the integers.
    *
    * @param b Other operand
    * @return Array with quotient first and then remainder
    */
   private LongInteger[] divideAndRemainderAbsolute(LongInteger x) {
      // Initialize quotient and remainder
      LongInteger b = x.abs();
      LongInteger r = this.abs();
      LongInteger q = new LongInteger();

      // Storage for the loop
      Integer qi, low, high;
      LongInteger temp, new_r, b_shifted;

      for (int i = this.length() - b.length(); i >= 0; --i) {

         // Compute shifted divisor for later use
         b_shifted = b.shift(i);
         //   System.out.printf("***** i = %d, x_shifted = %s\n", i, x_shifted.toString() );

         // Use binary search to find next digit
         low = Integer.valueOf(0);
         high = LongInteger.radix - 1;
         do {
            // Guess qi is average
            qi = (low + high) / 2;
            //    System.out.printf("** low = %s, high = %s, qi = %s\n", low.toString(), high.toString(), qi.toString() );

            // Delay subtraction until necessary
            temp = b_shifted.multiply(qi);
            //    System.out.printf( "temp = %s, r = %s\n", temp.toString(), r.toString() );
            // Is qi too large?
            if (temp.compare(r) > 0) {
               high = qi - 1;
            } else {
               // Know qi is not too large
               new_r = r.subtractSameSign(b_shifted.multiply(qi));
               //     System.out.printf( "new_r = %s, x_shifted = %s, comp = %d\n", new_r.toString(), x_shifted.toString(), new_r.compare( x_shifted ) );

               // Is qi too small?
               if (new_r.compare(b_shifted) >= 0) {
                  low = qi + 1;
               } else {
                  // qi just right, so stop loop
                  low = high + 1;
               }
            }
            //   System.out.printf(" low = %s, high = %s\n", low.toString(), high.toString() );
         } while (low <= high);

         // Update q and r
         q.digits.add(qi);
         r = r.subtractSameSign(temp);

         //   // Minimize length for efficiency
         //   q.minimizeLength();
         //   r.minimizeLength();

         //   System.out.printf( "i = %d, q = %s, r = %s\n", i, q.digits.toString(), r.toString() );
      }

      Collections.reverse(q.digits);
      q.minimizeLength();

      // Done
      LongInteger[] result = new LongInteger[2];
      result[0] = q;
      result[1] = r;
      return result;
   }

   /** Negation
    * Does not change this.
    *
    * @return Result as new LongInteger
    */
   private LongInteger changeSign() {
      //  if( LongInteger.modular() )
      //   return LongInteger.modulus.subtractSameSign( this );
      //  else
      // Return new LongInteger with same digits and opposite sign
      return new LongInteger(this.digits, !this.positive);
   }

   /** Negation
    * Does not change this.
    *
    * @return Result as new LongInteger
    */
   public LongInteger negate() {
      LongInteger result = this.changeSign();

      if( LongInteger.modular() )
         result = result.remainder( LongInteger.modulus );
      //  else
      // Return new LongInteger with same digits and opposite sign
      return result;
   }

   /** Absolute value of this
    *
    * @return
    */
   public LongInteger abs() {
      LongInteger result = new LongInteger(this);
      result.positive = true;
      return result;
   }

   /** Multiplicative inverse
    * Does not change this.
    *
    * @return Result as new LongInteger
    */
   public LongInteger inverse() {
      // Modular arithmetic?
      if( LongInteger.modular() ) { // No
         try {
            return LongInteger.ONE.divideMod(this);
         } catch( KernelException exception ) {
            throw new ParserException( "Integer " + this.toString() + " is not a unit modulo " + LongInteger.modulus() );
         }
      } else {
         if( this.abs().equals( LongInteger.ONE ) )
            return this;
         else
            throw new ParserException( "Integer " + this.toString() + " is not a unit" );
      }
   }

   @Override
   public String toString() {

      // Ensure minimum length
      //  this.minimizeLength();

      // If no digits, it is zero
      if (this.digits.isEmpty()) {
         return "0";
      }

      // If have digits, build a string from list of decimal digits.
      StringBuilder result = new StringBuilder();

      // Positive or negative?
      if (!positive) {
         result.append('-');
      }

      // Convert to decimal (not efficient)
      List<Integer> newDigits = LongInteger.convertBase(this.digits, LongInteger.radix, new Integer(10));

      // Reverse of ease of printing
      Collections.reverse(newDigits);

      // Add to result
      ListIterator<Integer> itr = newDigits.listIterator();
      while (itr.hasNext()) {
         result.append(itr.next().toString());
      }

      return result.toString();
   }

   /** Convert a list of digits from one base to another
    *
    * @param digits
    * @param fromRadix
    * @param toRadix
    * @return
    */
   public static ArrayList<Integer> convertBase(ArrayList<Integer> digits, Integer fromRadix, Integer toRadix) {
      ArrayList<Integer> result = new ArrayList<Integer>();

      // Reverse digits for ease in conversion
      ArrayList<Integer> revDigits = new ArrayList<Integer>(digits);  // Copy
      Collections.reverse(revDigits); // Reverse

      // Russian Peasant Method
      while (!revDigits.isEmpty()) {
         ArrayList<Integer> newRevDigits = new ArrayList<Integer>(); // Temporary storage
         ListIterator<Integer> itr = revDigits.listIterator();
         Integer quo;
         Integer rem = new Integer(0);

         // Divide by toRadix
         while (itr.hasNext()) {
            quo = itr.next() + rem * fromRadix;
            newRevDigits.add(quo / toRadix);
            rem = quo % toRadix;  // Carry the remainder
         }

         // Remainder is next digit of the result
         result.add(rem);

         // Prepare for next iteration
         revDigits = newRevDigits;

         // Eliminate leading zeros
         int i = 0;
         while (i < revDigits.size() && 0 == revDigits.get(i)) {
            ++i;
         }
         revDigits.subList(0, i).clear();
      }

      return result;
   }

   /** The traditional Extended Euclidean Algorithm.
    * Computes the GCD and corresponding coefficients of this and x via the
    * traditional algorithm and returns an array of three LongIntegers.
    * [0] is a GCD
    * [1] is the coefficient corresponding to this
    * [2] is the coefficient corresponding to x
    *
    * The computation is always done in the integers, not in modular arithmetic.
    * 
    * @param x
    * @return
    */
   public LongInteger[] traditionalEEA( LongInteger x ) {
      LongInteger[] result = new LongInteger[3];

      //Change modulus temporarily so we can get the correct result
      LongInteger tempModulus = modulus;
      changeModulus(LongInteger.ZERO);

      if(this.equals(LongInteger.ZERO)){
         result[0] = LongInteger.ZERO;
         result[1] = this;
         result[2] = x;
         return result;
      }
      else if(x.equals(LongInteger.ZERO)){
         result[0] = LongInteger.ZERO;
         result[1] = x;
         result[2] = this;
         return result;
      }


      LongInteger r = new LongInteger(x);
      r.positive = true;

      LongInteger s = LongInteger.ZERO;
      LongInteger t = LongInteger.ONE;
      LongInteger oldR = new LongInteger(this);
      oldR.positive = true;

      LongInteger oldS = LongInteger.ONE;
      LongInteger oldT = LongInteger.ZERO;

      while(!r.equals(LongInteger.ZERO)){
         LongInteger tempVal = new LongInteger();
         boolean sign = (oldR.positive == r.positive);
         LongInteger quotient = oldR.divideAndRemainderAbsolute(r)[0];
         //System.out.println("Quotient: " + quotient);
         quotient.positive = sign;
         LongInteger temp;
         //Update reminader

         temp = r;        
         //r = oldR.subtract(quotient.multiply(temp));
         //sign = oldR.positive;
         if(tempVal.positive){
            r = oldR.subtractSameSign(quotient.multiply(temp));
         }
         else{
            r = oldR.addSameSign(quotient.multiply(temp));
         }
         // r.positive = sign;
         oldR = temp;
         //System.out.println("r: " + r +" OldR: " + oldR);

         //Update S
         temp = s;
         tempVal = quotient.multiply(temp);
         //System.out.println("temp before: " + temp);
         //System.out.println("OldS before: " + oldS);
         // System.out.println("s tempVal: " + tempVal + " positive?: " +  tempVal.positive);

         if(oldS.positive){
            if(tempVal.positive){
               s = oldS.subtractSameSign(tempVal);
            }
            else{
               s = oldS.addSameSign(tempVal);
            }
         }
         else{
            if(tempVal.positive){
               s = oldS.addSameSign(tempVal.negate());
            }
            else{
               s = tempVal.subtractSameSign(oldS.negate());
            }
         }

         oldS = temp;
         //Update T
         temp = t;
         //sign = oldT.positive;
         tempVal = quotient.multiply(temp);
         if(oldT.positive){
            if(tempVal.positive){
               t = oldT.subtractSameSign(tempVal);
            }
            else{
               t = oldT.addSameSign(tempVal);
            }
         }
         else{
            if(tempVal.positive){
               //System.out.println("nega pos case");
               t = oldT.addSameSign(tempVal.negate());
            }
            else{
               t = tempVal.subtractSameSign(oldT.negate());
            }
<<<<<<< HEAD
        }
        //t = oldT.subtract(quotient.multiply(temp));
        //t = oldT.subtractSameSign(quotient.multiply(temp));
        //t.positive= sign;
        oldT = temp;
        //System.out.println("T: " + t +" OldT: " + oldT);
    }
    
    
    result[0] = oldR;
    result[1] = oldS;
    result[2] = oldT;
    //Change it back
    changeModulus(tempModulus);
    return result;
  
 }

 /** The Extended Euclidean Algorithm.
  * Computes the GCD and corresponding coefficients of this and x via the
  * Extended Euclidean algorithm and returns an array of three LongIntegers.
  * [0] is the GCD
  * [1] is the coefficient corresponding to this
  * [2] is the coefficient corresponding to x
  *
  * The computation is always done in the integers, not in modular arithmetic.
  * 
  * @param x
  * @return
  */
 public LongInteger[] EEA( LongInteger x ) {
  throw new UnsupportedOperationException( "EEA not yet supported" );
 }
 
 /** Greatest common divisor
  * 
  * @param x
  * @return 
  */
 public LongInteger gcd( LongInteger x ) {
  LongInteger result[] = traditionalEEA(x);
  return result[0];
 }
 
 /** Modular division
  * Does not change this.
  *
  * @param x Other operand
  * @return Result as new LongInteger
  */
 private LongInteger divideMod(LongInteger x) {
    LongInteger result = new LongInteger();
    LongInteger xInverse;
    //System.out.println("X: " +  x.toString() + " modulus:  " + modulus);
    LongInteger[] xInverseResult = modulus.traditionalEEA(x);
    //System.out.println(xInverseResult[0] + " " + xInverseResult[1] + " " + xInverseResult[2]);
    if(xInverseResult[0].compare(LongInteger.ZERO) != 0){
        xInverse = xInverseResult[2];
        //System.out.println("Inverse of " + x + " is: " + xInverse);
        result = this.multiply(xInverse);
    }
    else{
        throw new KernelException("Cannot divide " + this + "/" + x );
    }
    return result;
 }

 /** Raise to a power
  * Does not change this.
  *
  * @param l Power to raise this to 
  * @return Result as new LongInteger
  */
 public LongInteger pow(LongInteger l) {
  throw new UnsupportedOperationException("LongInteger pow not yet supported");
 }

 /** Evaluate the integer at a series of moduli
  * 
  * @param m Moduli at which to evaluate the integer
  * @return Array of values
  */ 
 public LongInteger[] evaluate( LongInteger[] m ) {
  throw new UnsupportedOperationException( "evaluate not yet supported" );
 }

 /** Chinese Remainder Algorithm
  * 
  * This algorithm assumes the moduli are all pair-wise co-prime.
  * 
  * @param v Array of values at moduli
  * @param m Array of moduli
  * @return 
  */ 
 public static LongInteger cra( LongInteger[] v, LongInteger[] m ) {
    LongInteger result = LongInteger.ZERO;
    LongInteger bigguy = LongInteger.ONE;
    for (int i = 0; i < m.length; ++i) bigguy = bigguy.multiply(m[i]);
    for (int i = 0; i < v.length; ++i) {
      LongInteger smallGuy = bigguy.divide(m[i]);
      LongInteger[] temp = m[i].traditionalEEA(smallGuy);
      result = result.addSameSign(v[i].multiply(smallGuy).multiply(temp[2])).remainder(bigguy);
    }
    return result;
 }

 /** Multiply by LongInteger x via Karatsuba algorithm
  * 
  * @param x Other operand
  * @return Result as new LongInteger
  */ 
 public LongInteger karatsuba( LongInteger x ) {
  throw new UnsupportedOperationException( "karatsuba not yet supported" );
 }

 /** p-adic inversion using Newton iteration.
  * Invert this modulo p^l given a starting solution g0
  * 
  * @param p an arbitrary LongInteger
  * @param l a power of p
  * @param g0 the inverse of this modulo p
  * @return 
  */
 public LongInteger inversion( LongInteger p, int l, LongInteger g0 ) {
  throw new UnsupportedOperationException( "inversion not yet supported" );
 }
=======
         }
         //t = oldT.subtract(quotient.multiply(temp));
         //t = oldT.subtractSameSign(quotient.multiply(temp));
         //t.positive= sign;
         oldT = temp;
         //System.out.println("T: " + t +" OldT: " + oldT);
      }


      result[0] = oldR;
      result[1] = oldS;
      result[2] = oldT;
      //Change it back
      changeModulus(tempModulus);
      return result;

   }

   /** The Extended Euclidean Algorithm.
    * Computes the GCD and corresponding coefficients of this and x via the
    * Extended Euclidean algorithm and returns an array of three LongIntegers.
    * [0] is the GCD
    * [1] is the coefficient corresponding to this
    * [2] is the coefficient corresponding to x
    *
    * The computation is always done in the integers, not in modular arithmetic.
    * 
    * @param x
    * @return
    */
   public LongInteger[] EEA( LongInteger x ) {
      throw new UnsupportedOperationException( "EEA not yet supported" );
   }

   /** Greatest common divisor
    * 
    * @param x
    * @return 
    */
   public LongInteger gcd( LongInteger x ) {
      LongInteger result[] = traditionalEEA(x);
      return result[0];
   }

   /** Modular division
    * Does not change this.
    *
    * @param x Other operand
    * @return Result as new LongInteger
    */
   private LongInteger divideMod(LongInteger x) {
      LongInteger result = new LongInteger();
      LongInteger xInverse;
      //System.out.println("X: " +  x.toString() + " modulus:  " + modulus);
      LongInteger[] xInverseResult = modulus.traditionalEEA(x);
      //System.out.println(xInverseResult[0] + " " + xInverseResult[1] + " " + xInverseResult[2]);
      if(xInverseResult[0].compare(LongInteger.ZERO) != 0){
         xInverse = xInverseResult[2];
         //System.out.println("Inverse of " + x + " is: " + xInverse);
         result = this.multiply(xInverse);
      }
      else{
         throw new KernelException("Cannot divide " + this + "/" + x );
      }
      return result;
   }

   /** Raise to a power
    * Does not change this.
    *
    * @param l Power to raise this to 
    * @return Result as new LongInteger
    */
   public LongInteger pow(LongInteger l) {
      //check if l is zero or one or negative power
      if(l.equals(LongInteger.ZERO)){
         return LongInteger.ONE;
      }
      else if(l.equals(LongInteger.ONE)){
         return this;
      }    
      LongInteger power = new LongInteger(l);
      LongInteger b = new LongInteger(this);
      //Check if l is negative
      //System.out.println("l.positive(): " + l.positive());
      if(!l.positive()){
         //   System.out.println("Neg power");
         b = this.inverse();
         power = power.negate();
      }
      LongInteger result = LongInteger.ONE;
      ArrayList<Integer> lBinary = LongInteger.convertBase(power.digits, radix, 2);
      for(int i = 0; i < lBinary.size(); i++){
         if(lBinary.get(i) == Integer.valueOf(1)){
            result = result.multiply(b);
         }
         b = b.multiply(b);
      }
      return result;
   }

   /** Evaluate the integer at a series of moduli
    * 
    * @param m Moduli at which to evaluate the integer
    * @return Array of values
    */ 
   public LongInteger[] evaluate( LongInteger[] m ) {
      LongInteger[] result = new LongInteger[m.length];
      LongInteger currentModulus = this.modulus();
      LongInteger.changeModulus(LongInteger.ZERO);
      for(int i = 0; i < m.length; i++){
         result[i] = this.remainder(m[i]);
      }
      LongInteger.changeModulus(currentModulus);
      return result;
   }

   /** Chinese Remainder Algorithm
    * 
    * This algorithm assumes the moduli are all pair-wise co-prime.
    * 
    * @param v Array of values at moduli
    * @param m Array of moduli
    * @return 
    */ 
   public static LongInteger cra( LongInteger[] v, LongInteger[] m ) {
      LongInteger result = LongInteger.ZERO;
      LongInteger bigguy = LongInteger.ONE;
      for (int i = 0; i < m.length; ++i) bigguy = bigguy.multiply(m[i]);
      for (int i = 0; i < v.length; ++i) {
         LongInteger smallGuy = bigguy.divide(m[i]);
         LongInteger[] temp = m[i].traditionalEEA(smallGuy);
		 result = result.addSameSign(((v[i].multiply(temp[2])).remainder(m[i])).multiply(smallGuy));
         //result = result.addSameSign((v[i].multiply(smallGuy).multiply(temp[2])).remainder(m[i]));
      }
      return result;
   }

   /** Multiply by LongInteger x via Karatsuba algorithm
    * 
    * @param x Other operand
    * @return Result as new LongInteger
    */ 
   public LongInteger karatsuba( LongInteger x ) {
      // throw new UnsupportedOperationException( "karatsuba not yet supported" );
      //Check if this or x is 0
      //
      if(this.equals(LongInteger.ZERO) || x.equals(LongInteger.ZERO)){
         return LongInteger.ZERO;
      }
      //Initialize bitlength and find larger length
      int bitLength = Math.max(x.length(), this.length());
      int originalBitLength = bitLength;
      //System.out.println("BitLength: " + bitLength);
      //System.out.println("In karatsuba function");
      //End recursion if bitlength =1
      if(bitLength < 2){
         // System.out.println("Base case");
         return this.multiply(x);
      }

      //Calculate bitlength, rounded up
      bitLength = (bitLength/2) + (bitLength % 2); 

      //System.out.println("Real BitLength: " + bitLength);
      //Define F_1
      LongInteger F_1 = this.shiftRight(bitLength);
      //System.out.println("this: " + this + " F_1: " + F_1);
      //System.out.println("F_1 length: " + F_1.length());
      //System.out.println("this: " + this);
      LongInteger F_0 = this.subtract(F_1.shift(bitLength));
      LongInteger G_1 = x.shiftRight(bitLength);
      LongInteger G_0 = x.subtract(G_1.shift(bitLength));
      //System.out.println("F_1: " + F_1 + " F_0:  " + F_0 + " G_1: " +G_1 + " G_0: " + G_0);

      LongInteger F_0G_0 = F_0.karatsuba(G_0);
      LongInteger F_1G_1 = F_1.karatsuba(G_1);
      LongInteger F_0F_1G_0G_1 = F_0.add(F_1).karatsuba(G_0.add(G_1));

      return (F_0F_1G_0G_1.subtract(F_0G_0).subtract(F_1G_1)).shift(bitLength).add(F_0G_0).add(F_1G_1.shift(bitLength*2)); 
   }
   private LongInteger shiftRight(int power){
      // System.out.println("Shift right by " + power);
      if(power >= this.length()){
         return LongInteger.ZERO;
      }
      ArrayList<Integer> resultList = new ArrayList<Integer>();
      resultList.addAll(this.digits);
      //System.out.println("Before shift: " + resultList.toString());
      resultList.subList(0, power).clear();
      //System.out.println("After shift: " + resultList.toString());
      LongInteger result = new LongInteger(resultList);
      return result;

   }
   /** p-adic inversion using Newton iteration.
    * Invert this modulo p^l given a starting solution g0
    * 
    * @param p an arbitrary LongInteger
    * @param l a power of p
    * @param g0 the inverse of this modulo p
    * @return 
    */
   public LongInteger inversion( LongInteger p, int l, LongInteger g0 ) {
      throw new UnsupportedOperationException( "inversion not yet supported" );
   }
>>>>>>> master

}

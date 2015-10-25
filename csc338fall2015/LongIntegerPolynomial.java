/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

//import java.math.LongInteger;
import static csc338fall2015.LongInteger.changeModulus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Single variable polynomial implemented as an array of coefficients.
 *
 * @param R Coefficient ring
 *
 * @author turnerw
 */
public class LongIntegerPolynomial {

    // Declare first so can use later
    public static LongIntegerPolynomial ZERO = LongIntegerPolynomial.valueOf(0);
    public static LongIntegerPolynomial ONE = LongIntegerPolynomial.valueOf(1);
    public static LongIntegerPolynomial X = LongIntegerPolynomial.valueOf(1).shift(1);
    /**
     * Coefficient array implemented as an array of ring elements.
     *
     */
    public ArrayList<LongInteger> coeffs;
    /**
     * Modulus for polynomial modular arithmetic (extension field);
     *
     */
    private static LongIntegerPolynomial modulus = LongIntegerPolynomial.valueOf(0);
// private static LongIntegerPolynomial modulus = LongIntegerPolynomial.X;

    /**
     * Default constructor. Creates an empty polynomial with no non-zero
     * coefficients.
     */
    public LongIntegerPolynomial() {
        this.coeffs = new ArrayList<LongInteger>();
    }

    /**
     * Copy constructor
     *
     * @param f Polynomial to copy
     */
    public LongIntegerPolynomial(LongIntegerPolynomial f) {
        this.coeffs = new ArrayList<LongInteger>(f.coeffs);
        this.ensureLeadingCoefficient();
    }

    /**
     * Constructor from a coefficient list.
     *
     * @param c List of coefficients of the polynomial to construct.
     */
    public LongIntegerPolynomial(List<LongInteger> c) {
        this.coeffs = new ArrayList<LongInteger>(c);
        this.ensureLeadingCoefficient();
    }

    /**
     * Constructor from an array of coefficients.
     *
     * @param ints Array of coefficients of the polynomial to construct.
     */
    public LongIntegerPolynomial(LongInteger[] ints) {
        this.coeffs = new ArrayList<LongInteger>();
        for (int i = 0; i < ints.length; ++i) {
            this.coeffs.add(ints[i]);
        }

        this.ensureLeadingCoefficient();
    }

    /**
     * Create a LongIntegerPolynomial from a value.
     *
     * @param val
     * @return
     */
    public static LongIntegerPolynomial valueOf(LongInteger val) {
        LongIntegerPolynomial result = new LongIntegerPolynomial();

        // Put on constant term only
        if (!val.equals(LongInteger.ZERO)) {
            result.coeffs.add(val);
        }

        // Done
        return result;
    }

    /**
     * Create a LongIntegerPolynomial from a value.
     *
     * @param val
     * @return
     */
    public static LongIntegerPolynomial valueOf(int val) {
        LongIntegerPolynomial result = new LongIntegerPolynomial();

        // Put on constant term only
        if (val != 0) {
            result.coeffs.add(LongInteger.valueOf(val));
        }

        // Done
        return result;
    }

    public static LongIntegerPolynomial modulus() {
        return LongIntegerPolynomial.modulus;
    }

    public static LongIntegerPolynomial modulus(LongIntegerPolynomial mod) {
        return LongIntegerPolynomial.modulus = new LongIntegerPolynomial(mod);
    }

    public static LongIntegerPolynomial changeModulus(LongIntegerPolynomial x) {
        modulus = x;
        return x;
    }

    /**
     * Using modular arithmetic? Checks without using the compare or equals
     *
     * @return
     */
    public static boolean modular() {
        return !LongIntegerPolynomial.modulus.coeffs.isEmpty();
    }

    /**
     * Minimizes the size of the coefficient list so the leading coefficient is
     * the last entry in the array.
     */
    private void ensureLeadingCoefficient() {
        int i = this.coeffs.size();

        while (i > 0 && this.coeffs.get(--i).equals(LongInteger.ZERO)) {
            this.coeffs.remove(i);
        }
    }

    /**
     * Degree function
     *
     * @return degree of this polynomial. -1 if zero polynomial
     */
    public int degree() {
        return this.coeffs.size() - 1;
    }

    /**
     * Convert polynomial from string
     *
     * @param s Input string
     * @return This polynomial after the conversion
     */
    public LongIntegerPolynomial fromString(String s) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String toString() {
        return this.coeffs.toString();
    }

    public List< LongInteger> coefficients() {
        return this.coeffs;
    }

    public boolean equals(LongIntegerPolynomial poly) {

        this.ensureLeadingCoefficient();
        poly.ensureLeadingCoefficient();

        // Lengths must be the same
        if (this.coeffs.size() != poly.coeffs.size()) {
            return false;
        }

        // Iterate down lists
        for (int i = 0; i < this.coeffs.size(); ++i) {
            if (!this.coeffs.get(i).equals(poly.coeffs.get(i))) {
                return false;
            }
        }

        // Must be equal!
        return true;

        //return this.coeffs.equals( poly.coeffs ); // Why doesn't this work?


    }

    /**
     * Polynomial addition
     *
     * @param poly
     * @return
     */
    public LongIntegerPolynomial add(LongIntegerPolynomial poly) {

        LongIntegerPolynomial result = new LongIntegerPolynomial();

        // Find minimal degree of the two
        int smallerDegree = (this.coeffs.size() < poly.coeffs.size()) ? this.coeffs.size() : poly.coeffs.size();
//  System.out.println( "Smaller degree = " + smallerDegree );

        // Add terms that overlap
        for (int i = 0; i < smallerDegree; ++i) {
            result.coeffs.add(this.coeffs.get(i).add(poly.coeffs.get(i)));
        }

        // Append remaining terms
        if (this.coeffs.size() > smallerDegree) {
            result.coeffs.addAll(this.coeffs.subList(smallerDegree, this.coeffs.size()));
        } else if (poly.coeffs.size() > smallerDegree) {
            result.coeffs.addAll(poly.coeffs.subList(smallerDegree, poly.coeffs.size()));
        } else // Equal degrees, may have canceled leading term
        {
            result.ensureLeadingCoefficient();
        }

        // Modular arithmetic?
//  System.out.println("adding with modulus " + LongIntegerPolynomial.modulus );
        if (!LongIntegerPolynomial.modulus.equals(LongIntegerPolynomial.ZERO)) {
            result = result.remainder(LongIntegerPolynomial.modulus);
        }

        return result;
    }

    /**
     * Scalar multiplication without modular arithmetic This function is
     * necessary to allow non-modular division, which is necessary to find the
     * remainder during modular arithmetic.
     *
     * @param a Scalar by which to multiply the polynomial
     * @return Polynomial scaled by the scalar
     */
    private LongIntegerPolynomial multiplyNoMod(LongInteger a) {

        LongIntegerPolynomial result = new LongIntegerPolynomial();

        for (int i = 0; i < this.coeffs.size(); ++i) {
            result.coeffs.add(a.multiply(this.coeffs.get(i)));
        }

        return result;
    }

    /**
     * Scalar multiplication
     *
     * @param a Scalar by which to multiply the polynomial
     * @return Polynomial scaled by the scalar
     */
    public LongIntegerPolynomial multiply(LongInteger a) {

        // Call the nonmodular version
        LongIntegerPolynomial result = this.multiplyNoMod(a);

        // Modular arithmetic?
        if (!LongIntegerPolynomial.modulus.equals(LongIntegerPolynomial.ZERO)) {
            result = result.remainder(LongIntegerPolynomial.modulus);
        }

        return result;
    }

    /**
     * Negation
     *
     * @return This polynomial with all coefficients negated.
     */
    public LongIntegerPolynomial negate() {
//  return this.multiply(LongInteger.ZERO.subtract(LongInteger.ONE));
        LongIntegerPolynomial result = new LongIntegerPolynomial();
        for (int i = 0; i < this.coeffs.size(); ++i) {
            result.coeffs.add(this.coeffs.get(i).negate());
        }

        return result;
    }

    /**
     * Subtration
     *
     * @param poly
     * @return
     */
    public LongIntegerPolynomial subtract(LongIntegerPolynomial poly) {
        return this.add(poly.negate());
    }

    /**
     * Multiplication by another polynomial
     *
     * @param poly
     * @return
     */
    public LongIntegerPolynomial multiply(LongIntegerPolynomial poly) {
        // Use polynomial degree to make decisions
        int deg = poly.degree();

        if (deg < 0) // Zero polynomial
        {
            return new LongIntegerPolynomial();
        }

//  if( deg == 0 ) // Scalar multiplication
//   return this.multiply( poly.coeffs.get(0) );
//
    /* Multiplying by a real polynomial.  Use the classical algorithm via
         * a Horner-like rule.
         */
        LongIntegerPolynomial result = this.multiply(poly.coeffs.get(deg));
        for (int i = deg - 1; i >= 0; --i) {
            result.coeffs.add(0, LongInteger.ZERO); // Multiply by x
            result = result.add(this.multiply(poly.coeffs.get(i)));
        }

        // Modular arithmetic done in the additions
        // Modular arithmetic?
        if (!LongIntegerPolynomial.modulus.equals(LongIntegerPolynomial.ZERO)) {
            result = result.remainder(LongIntegerPolynomial.modulus);
        }


        return result;
    }

    public LongInteger leadingCoefficient() {
        this.ensureLeadingCoefficient();
        if (this.coeffs.isEmpty()) //   return LongInteger.ZERO;
        {
            return LongInteger.ONE;  // Leading unit of zero polyomial is one
        } else {
            return this.coeffs.get(this.coeffs.size() - 1);
        }
    }

    /**
     * Multiply this by a power of x
     *
     * @param power
     * @return
     */
    private LongIntegerPolynomial shift(int power) {
        // Create empty result
        LongIntegerPolynomial result = new LongIntegerPolynomial();

        // Add zeroes for the shift
        for (int k = 0; k < power; ++k) {
            result.coeffs.add(LongInteger.ZERO);
        }

        // Now add all the coeffcients of this
        result.coeffs.addAll(this.coeffs);

        // Done
        return result;
    }

    /**
     * Division by another polynomial
     *
     * @param poly
     * @return
     */
    public LongIntegerPolynomial divide(LongIntegerPolynomial poly) {
        if (!LongIntegerPolynomial.modular()) {
            return this.divideAndRemainder(poly)[0];
        } else {
            return this.divideMod(poly);
        }
    }

    /**
     * Remainder after division Does not change this.
     *
     * @param x Other operand
     * @return Result as new LongIntegerPolynomial
     */
    public LongIntegerPolynomial remainder(LongIntegerPolynomial x) {
        return this.divideAndRemainder(x)[1];
    }

    /**
     * Division by another polynomial, returning both the quotient and the
     * remainder.
     *
     * @param poly
     * @return Array with the quotient first and then the remainder
     */
    public LongIntegerPolynomial[] divideAndRemainder(LongIntegerPolynomial poly) {
        LongIntegerPolynomial[] returnArray = new LongIntegerPolynomial[2];
        if (modular()) {
            System.out.println("Modular Boys");
            // Ensure not dividing by zero
            int polyDegree = poly.degree();
            if (polyDegree < 0) {
                throw new KernelException("Cannot divide by the zero polynomial");
            }

            // Create result
            LongIntegerPolynomial q = new LongIntegerPolynomial();

            // Initialize the loop
            LongIntegerPolynomial r = new LongIntegerPolynomial(this);
            LongInteger qi;

            // Can we divide?
            LongInteger u;
            try {
                u = poly.leadingCoefficient().inverse();
            } catch (ParserException exception) {
                throw new ParserException("Cannot divide by " + poly.toString() + " because " + exception.getLocalizedMessage());
            }


            // Check if leading coefficient of poly is a unit
            if (!LongInteger.ONE.equals(u.multiply(poly.leadingCoefficient()))) {
                throw new KernelException("Cannot divide because leading coefficient is not a unit");
            }

            // Loop

            for (int i = this.degree() - poly.degree(); i >= 0; --i) {
                if (r.degree() == poly.degree() + i) {
                    qi = r.leadingCoefficient().multiply(u);
                    q.coeffs.add(qi);
                    r = r.subtract(poly.shift(i).multiplyNoMod(qi));
                } else {
                    q.coeffs.add(LongInteger.ZERO);
                }
            }

            Collections.reverse(q.coeffs);

            // Done
            returnArray[0] = q;
            returnArray[1] = r;
        } 
        else {
            ArrayList<LongInteger> quotient = new ArrayList<LongInteger>();
            
            if (this.degree() < poly.degree()) {
                //System.out.println("this.degree: " + this.degree() + " poly.degree: " + poly.degree());
                //System.out.println("Instant exit");
                returnArray[0] = LongIntegerPolynomial.ZERO;
                returnArray[1] = this;
                return returnArray;
            }
            LongIntegerPolynomial denominator = poly;
            LongIntegerPolynomial numerator = this;
            quotient = numerator.coeffs;
            ArrayList<LongInteger> returnDigitList = new ArrayList<LongInteger>(Collections.nCopies(this.degree() - poly.degree() + 1, LongInteger.valueOf(0)));
            //ArrayList<LongInteger> returnDigitList = new ArrayList<LongInteger>(this.degree() - poly.degree() + 1);
            
            
            
            LongInteger denomLeadingDigit = denominator.coeffs.get(poly.degree());
            for (int i = 0; i <= numerator.degree(); i++) {
                System.out.println("Quotient: " + quotient);
                LongInteger numLeadingDigit = quotient.get(quotient.size() - 1);

                LongInteger trialDigit = numLeadingDigit.divide(denomLeadingDigit);
                System.out.println("Trial Digit: " + trialDigit.toString());

                ArrayList<LongInteger> tempList = new ArrayList<LongInteger>();
                tempList.add(trialDigit);
                LongIntegerPolynomial trialPoly = new LongIntegerPolynomial(tempList);
                int difference = new LongIntegerPolynomial(quotient).degree() - denominator.degree();
                System.out.println("difference: " + difference);
                if (difference < 0) {
                    break;
                }

                trialPoly = trialPoly.shift((new LongIntegerPolynomial(quotient)).degree() - denominator.degree());
                System.out.println("trialPoly.degree() - i : " + (trialPoly.degree() - i));
                
                
                //System.out.println("Denom" + denominator.toString());
                //System.out.println("Multiply denom: " + denominator.multiply(trialPoly).toString());
                System.out.println("Trial Poly: " + trialPoly.toString());
                LongIntegerPolynomial tempQuotient = (new LongIntegerPolynomial(quotient)).subtract(denominator.multiply(trialPoly));
               System.out.println("tempQuo: " + tempQuotient.toString());
                quotient = tempQuotient.coeffs;
                //System.out.println("Bottom loop quotient: " + quotient);
                returnDigitList.set(trialPoly.degree(), trialDigit);
                //returnDigitList.add(0, trialDigit);
                if (new LongIntegerPolynomial(quotient).equals(LongIntegerPolynomial.ZERO)) {
                    //System.out.println("Exit");
                    break;
                }
            }

            returnArray[0] = new LongIntegerPolynomial(returnDigitList);
            //System.out.println("ReturnDigitList" + returnArray[0].toString());
            returnArray[1] = new LongIntegerPolynomial(quotient);
        }


        return returnArray;
    }

    /**
     * Multiplicative inverse Does not change this.
     *
     * @return Result as new LongIntegerPolynomial
     */
    public LongIntegerPolynomial inverse() {
        // Modular arithmetic?
        if (LongIntegerPolynomial.modulus.equals(LongIntegerPolynomial.ZERO)) { // No
            // Only units are constant polynomials and units in coefficient ring
            if (this.degree() == 0) {
                return LongIntegerPolynomial.valueOf(this.coeffs.get(0).inverse());
            } else {
                throw new ParserException("Integer polynomial " + this.toString() + " is not a unit");
            }
        } else { // Modular arithmetic
            return LongIntegerPolynomial.ONE.divide(this);
        }
    }

    /**
     * The traditional Extended Euclidean Algorithm. Computes the GCD and
     * corresponding coefficients of this and x via the traditional algorithm
     * and returns an array of three LongIntegers. [0] is a GCD [1] is the
     * coefficient corresponding to this [2] is the coefficient corresponding to
     * x
     *
     * @param x
     * @return
     */
    public LongIntegerPolynomial[] traditionalEEA(LongIntegerPolynomial x) {
        LongIntegerPolynomial[] result = new LongIntegerPolynomial[3];

        //Change modulus temporarily
        LongIntegerPolynomial tempModulus = modulus;
        changeModulus(LongIntegerPolynomial.ZERO);

        if (this.equals(LongIntegerPolynomial.ZERO)) {
            result[0] = LongIntegerPolynomial.ZERO;
            result[1] = this;
            result[2] = x;
            return result;
        } else if (x.equals(LongIntegerPolynomial.ZERO)) {
            result[0] = LongIntegerPolynomial.ZERO;
            result[1] = x;
            result[2] = this;
            return result;
        }


        LongIntegerPolynomial r = x;
        LongIntegerPolynomial s = LongIntegerPolynomial.ZERO;
        LongIntegerPolynomial t = LongIntegerPolynomial.ONE;
        LongIntegerPolynomial oldR = this;
        LongIntegerPolynomial oldS = LongIntegerPolynomial.ONE;
        LongIntegerPolynomial oldT = LongIntegerPolynomial.ZERO;

        while (!r.equals(LongIntegerPolynomial.ZERO)) {
            System.out.println("OldR: " + oldR + " r: " + r);
            LongIntegerPolynomial quotient = oldR.divideAndRemainder(r)[0];

            System.out.println("quotient: "+ quotient);
            
            LongIntegerPolynomial temp;
            //Update reminader
            temp = r;
            r = oldR.subtract(quotient.multiply(temp));
            oldR = temp;
            System.out.println("OldR: " + oldR + " r: " + r);
            //Update S
            temp = s;
            s = oldS.subtract(quotient.multiply(temp));
            oldS = temp;
            //Update T
            temp = t;
            t = oldT.subtract(quotient.multiply(temp));
            oldT = temp;
        }
        //normalize the polynomial; makes it easier to check with mathematica
        LongInteger normalizeCoeff = oldR.leadingCoefficient();
        oldR = oldR.multiply(normalizeCoeff.inverse());

        result[0] = oldR;
        result[1] = oldS.multiply(normalizeCoeff.inverse());
        result[2] = oldT.multiply(normalizeCoeff.inverse());
        changeModulus(tempModulus);
        return result;
    }

    /**
     * The Extended Euclidean Algorithm. Computes the GCD and corresponding
     * coefficients of this and x via the Extended Euclidean algorithm and
     * returns an array of three LongIntegers. [0] is the GCD [1] is the
     * coefficient corresponding to this [2] is the coefficient corresponding to
     * x
     *
     * @param x
     * @return
     */
    public LongIntegerPolynomial[] EEA(LongIntegerPolynomial x) {
        throw new UnsupportedOperationException("EEA not yet supported");
    }

    /**
     * Greatest common divisor
     *
     * @param x
     * @return
     */
    public LongIntegerPolynomial gcd(LongIntegerPolynomial x) {
        LongIntegerPolynomial result[] = traditionalEEA(x);
        return result[0];
    }

    /**
     * Modular division Does not change this.
     *
     * @param x Other operand
     * @return Result as new LongInteger
     */
    public LongIntegerPolynomial divideMod(LongIntegerPolynomial x) {
        LongIntegerPolynomial result = new LongIntegerPolynomial();
        LongIntegerPolynomial xInverse;
        //System.out.println("X: " + x.toString() + " modulus:  " + modulus);
        LongIntegerPolynomial[] xInverseResult = modulus.traditionalEEA(x);
        //System.out.println(xInverseResult[0] + " " + xInverseResult[1] + " " + xInverseResult[2]);
        if (!xInverseResult[0].equals(LongIntegerPolynomial.ZERO)) {
            xInverse = xInverseResult[2];
            //System.out.println("Inverse of " + x + " is: " + xInverse);
            result = this.multiply(xInverse);
        } else {
            throw new KernelException("Cannot divide " + this + "/" + x);
        }
        return result;
    }

    /**
     * Raise to a power Does not change this.
     *
     * @param l Power to raise this to
     * @return Result as new LongIntegerPolynomial
     */
    public LongIntegerPolynomial pow(LongInteger l) {
        throw new UnsupportedOperationException("pow not yet supported");
    }

    /**
     * Evaluate the polynomial at a value using Horner's rule.
     *
     * @param u
     * @return
     */
    public LongInteger apply(LongInteger u) {
        throw new UnsupportedOperationException("apply not yet supported");
    }

    /**
     * Evaluate the polynomial at a series of values
     *
     * @param u Values at which to evaluate the polynomial
     * @return Array of values
     */
    public LongInteger[] evaluate(LongInteger[] u) {
        throw new UnsupportedOperationException("evaluate not yet supported");
    }

    /**
     * Lagrange Interpolants
     *
     * @param u Values at which to create Lagrange Interpolants
     * @return Array of interpolants
     */
    public static LongIntegerPolynomial[] lagrangeInterpolants(LongInteger[] u) {
        throw new UnsupportedOperationException("lagrangeIterpolants not yet supported");
    }

    /**
     * Lagrange Interpolating Polynomial
     *
     * @param u Values at which to interpolate
     * @param v Values function to have at those points
     * @return
     */
    public static LongIntegerPolynomial interpolate(LongInteger[] u, LongInteger[] v) {
        throw new UnsupportedOperationException("interpolate not yet supported");
    }

    /**
     * Chinese Remainder Algorithm
     *
     * @param v Array of values at moduli
     * @param m Array of moduli
     * @return
     */
    public static LongIntegerPolynomial cra(LongIntegerPolynomial[] v, LongIntegerPolynomial[] m) {
        throw new UnsupportedOperationException("CRA not yet supported");
    }

    /**
     * Multiply by LongIntegerPolynomialPolynomial poly via Karatsuba algorithm
     *
     * @param poly Other operand
     * @return Result as new LongIntegerPolynomial
     */
    public LongIntegerPolynomial karatsuba(LongIntegerPolynomial poly) {
        throw new UnsupportedOperationException("karatsuba not yet supported");
    }

    /**
     * Reversal of polynomial Assume k >= degree of this
     *
     * @param k degree of the reversal
     * @return
     */
    public LongIntegerPolynomial reversal(int k) {
        throw new UnsupportedOperationException("reversal not yet supported");
    }

    /**
     * Inversion of the polynomial via Newton iteration Computes the inverse
     * modulo a power of x Requires the constant term to be one
     *
     * @param k the power of the modulus
     * @return
     */
    public LongIntegerPolynomial inversion(int l) {
        throw new UnsupportedOperationException("inversion not yet supported");
    }

    /**
     * Fast division with remainder via Newton iteration
     *
     * @param poly Other operand
     * @return Result as a new LongIntegerPolynomial
     */
    public LongIntegerPolynomial[] fastDivideAndRemainder(LongIntegerPolynomial poly) {
        throw new UnsupportedOperationException("not supported yet");
    }

    /**
     * p-adic inversion using Newton iteration. Invert this modulo p^l given a
     * starting solution g0
     *
     * @param p an arbitrary LongIntegerPolynomial
     * @param l a power of p
     * @param g0 the inverse of this modulo p
     * @return
     */
    public LongIntegerPolynomial inversion(LongIntegerPolynomial p, int l, LongIntegerPolynomial g0) {
        throw new UnsupportedOperationException("inversion not yet supported");
    }

    /**
     * Formal derivative
     *
     * @return
     */
    public LongIntegerPolynomial derivative() {
        throw new UnsupportedOperationException("derivative not yet supported");
    }

    /**
     * p-adic Newton iteration. Find a root of this modulo p^l
     *
     * @param p an arbitrary LongInteger
     * @param l a power of p
     * @param g0 an initial solution: a root modulo p
     * @param s0 the inverse of the derivative of this evaluated at g0 modulo p
     * @return
     */
    public LongInteger newton(LongInteger p, int l, LongInteger g0, LongInteger s0) {
        throw new UnsupportedOperationException("newton not yet supported");
    }

    /**
     * Compute minimal generating polynomial of a sequence given the beginning
     * of the sequence. Here we assume enough entries of the sequence are given
     * to determine the minimal generating polynomial.
     *
     * @param a The beginning of the linearly recurrent sequence
     * @return minimal generating polynomial of the sequence
     */
    public static LongIntegerPolynomial minpoly(List<LongInteger> a) {
        throw new UnsupportedOperationException("not yet supported");
    }
}

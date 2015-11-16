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


public class AlbertProj4Functions{
	public AlbertProj4Functions(){
	
	}
	/**
     * Reversal of polynomial Assume k >= degree of this
     *
     * @param k degree of the reversal
     * @return
     */
    public static LongIntegerPolynomial reversal(LongIntegerPolynomial input, int k) {
        LongIntegerPolynomial inputPoly = new LongIntegerPolynomial(input);
		LongIntegerPolynomial result = new LongIntegerPolynomial();		
		ArrayList<LongInteger> reversedInput = (ArrayList<LongInteger>) inputPoly.coefficients();
		Collections.reverse(reversedInput);
		
        if(k > input.degree()){
            ArrayList<LongInteger> returnList = (ArrayList<LongInteger>) Collections.nCopies(k-input.degree(), LongInteger.ZERO);
			returnList.addAll(reversedInput);
			result = new LongIntegerPolynomial(returnList);
        }
		else if(k < input.degree()){
			ArrayList<LongInteger> returnList = (ArrayList<LongInteger>) reversedInput.subList(k, reversedInput.size());
			result = new LongIntegerPolynomial(returnList);
			for(int i = 0; i < k;i++){
				LongInteger[] tempList = {LongInteger.ZERO, LongInteger.ONE};
				LongIntegerPolynomial tempPoly = new LongIntegerPolynomial(tempList);
				tempPoly = tempPoly.pow(LongInteger.valueOf(i-k));
				tempPoly = tempPoly.multiply(reversedInput.get(i));
				result = result.add(tempPoly);
			}
		}
		else{
			result = new LongIntegerPolynomial(reversedInput);
		}
		return result;
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
}
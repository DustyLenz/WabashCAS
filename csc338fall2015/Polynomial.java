/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

//import java.math.LongInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author turnerw
 */
public class Polynomial extends Expression implements Function {

	LongIntegerPolynomial poly;

	/** Default constructor
	 *
	 */
	public Polynomial() {
		this.poly = new LongIntegerPolynomial();
	}

	public Polynomial( LongIntegerPolynomial poly ) {
		this.poly = poly;
	}

	/** Construct a polynomial from an expression with a given variable.
	 * Coefficients must be values.  Other names are not evaluated and will
	 * cause an error to be thrown.
	 *
	 * @param exp	Expression containing polynomial
	 * @param var	Variable used in the polynomial
	 * @throws ParserException
	 */
	public Polynomial( Expression exp, Word var ) throws ParserException {

//		System.out.println( "Constructing polynomial from " + exp.toString() );

		ArrayList< LongInteger > coeffs = new ArrayList< LongInteger >();

		if( exp instanceof Value ) {
			// Make constant polynomial
			coeffs.add( ((Value) exp).value() );
			this.poly = new LongIntegerPolynomial( coeffs );
		} else if( exp instanceof Word ) {
//			System.out.println( "Polynomial from word " + exp.toString() );

			if( var.equals(exp) ) {
//				System.out.println( "Equals the variable!");
				// Make constant polynomial
				coeffs.add(LongInteger.ZERO);
				coeffs.add(LongInteger.ONE);
				this.poly = new LongIntegerPolynomial( coeffs );
			} else
				throw new ParserException( "Unknown constant " + exp.toString() );

		} else if( exp.operator != null ) {
			// Make polynomial based on which operator
			if( exp.operator.equals( Operator.Type.ADD ) ) {
				Polynomial poly1 = new Polynomial( exp.operand1, var );
				Polynomial poly2 = new Polynomial( exp.operand2, var );
				this.poly = poly1.poly.add( poly2.poly );
			} else if( exp.operator.equals( Operator.Type.SUBTRACT ) ) {
				Polynomial poly1 = new Polynomial( exp.operand1, var );
				Polynomial poly2 = new Polynomial( exp.operand2, var );
				this.poly = poly1.poly.subtract( poly2.poly );
			} else if( exp.operator.equals( Operator.Type.EXPONENT ) ) {
				// Monomial
				if( !( exp.operand2 instanceof Value ))
					throw new ParserException( "Power must be an integer " );

				if( !var.equals( exp.operand1 ) )
					throw new ParserException( "Only powers of variables are allowed in polynomials");

//				for( int i = 0; i < ((Value) exp.operand2).value().intValue(); ++i )
				for( LongInteger li = LongInteger.ZERO; li.compare( ( (Value) exp.operand2).value() ) < 0; li = li.add( LongInteger.ONE ) )
					coeffs.add( LongInteger.ZERO );

				coeffs.add( LongInteger.ONE );

				this.poly = new LongIntegerPolynomial( coeffs );
			} else if( exp.operator.equals( Operator.Type.MULTIPLY ) ) {

				if( exp.operand1 instanceof Value ) {
					Value coeff = (Value) exp.operand1;
					LongInteger scalar = coeff.value();

					Polynomial poly1 = new Polynomial( exp.operand2, var );
					this.poly = poly1.poly.multiply( scalar );
				} else {
					Polynomial poly1 = new Polynomial( exp.operand1, var );
					Polynomial poly2 = new Polynomial( exp.operand2, var );
					this.poly = poly1.poly.multiply( poly2.poly );
				}

			} else if( exp.operator.equals( Operator.Type.UNARY_MINUS ) ) {
				Polynomial poly1 = new Polynomial( exp.operand1, var );
				this.poly = poly1.poly.negate();
			} else {
				System.out.println( "Cannot make the polynomial " + exp.toString() );
				throw new ParserException("Illegal operator " + exp.operator.toString() );
			}
		}  else { // Not a value, the variable, or an operator
			System.out.println( "Cannot make the polynomial " + exp.toString() );
			throw new ParserException("Unknown polynomial form " + exp.toString() );
		}
	}

	@Override
	public Expression add(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial(this.poly.add( newPoly.poly ));
		} else if( exp instanceof Value ) {
			Polynomial newPoly = new Polynomial(exp, new Word("foo"));
//			System.out.println( "Created polynomial " + newPoly.toString() );
			return new Polynomial(this.poly.add( newPoly.poly ));
		} else
			return super.add( exp );
	}

	@Override
	public Expression subtract(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial(this.poly.subtract( newPoly.poly ));
		} else if( exp instanceof Value ) {
			Polynomial newPoly = new Polynomial(exp, new Word("foo"));
//			System.out.println( "Created polynomial " + newPoly.toString() );
			return new Polynomial(this.poly.subtract( newPoly.poly ));
		} else
			return super.subtract( exp );
	}

	@Override
	public Expression multiply(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial(this.poly.multiply( newPoly.poly ));
		} else if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Polynomial(this.poly.multiply( num.value() ));
		} else
			return super.multiply( exp );
	}

	@Override
	public Expression divide(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial(this.poly.divide( newPoly.poly ));
		} else
			return super.multiply( exp );
	}

	@Override
	public Expression remainder(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial( this.poly.remainder( newPoly.poly ));
		} else
			return super.remainder( exp );
	}

	@Override
	public Expression gcd(Expression exp) {
		if( exp instanceof Polynomial ) {
			Polynomial newPoly = (Polynomial) exp;
			return new Polynomial( this.poly.gcd( newPoly.poly ));
		} else
			return super.gcd( exp );
	}

	@Override
	public Expression apply(Value value) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Expression apply(Expression exp, Context cont) {

		// If zero polynomial, return zero
		if( this.poly.degree() < 0 )
			return new Value( LongInteger.ZERO );

		// Evaluate the expression if possible.
		Expression param = exp.evaluate(cont);

		// If value, call other apply
//		if( param instanceof Value )
//			return this.apply( (Value) param );


		// Otherwise create an expression from the coefficients
		List< LongInteger > coeffs = this.poly.coefficients();
		ListIterator< LongInteger > itr = coeffs.listIterator();
		Expression result = new Value( itr.next() );	// Must have at least constant
		LongInteger I = LongInteger.ZERO;
		Value coeff;

		while( itr.hasNext() ) {
			coeff = new Value( itr.next() );
			I = I.add( LongInteger.ONE );

			result = result.add( coeff.multiply(exp.pow( new Value(I) ) ) );
		}

		return result;

//		throw new UnsupportedOperationException("Not supported yet.");
	}


	/** Evaluate the polynomial in the given context.
	 * This does not change the original expression.
	 *
	 * @param context Context in which to evaluate the expression.
	 * @return Evaluated expression.
`	 *
	 * @param context Context in which to evaluate the expression.
	 * @return Evaluated expression.
	 * @throws KernelException
	 */
	@Override
	public Expression evaluate( Context context ) throws KernelException {

//		System.out.println( "Called evaluate polynomial on " + this.toString() );

		// Create string of coefficients to print

//		return new Word( "Polynomial " + this.poly.toString() );
		return this;
	}

	public LongIntegerPolynomial value() { return this.poly; }
	
	@Override
	public String toString() {
		return "Polynomial " + this.poly.toString();
	}
}

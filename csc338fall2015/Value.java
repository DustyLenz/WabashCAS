/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

//import java.math.LongInteger;

/**
 *
 * @author turnerw
 */
public class Value extends Expression {

    LongInteger value;
	boolean exact;

    /** Constructor from a string
     * Determines the appropriate type from the string.
     *
     * @param s String representing the token
     * @throws ParserException
     */
    public Value( String s ) throws ParserException {

//        System.out.printf( "Creating NumberToken from string '%s'\n", s );

        try {
            // If no decimal point, must be an LongInteger
            if( s.indexOf('.') == -1 ) {
                this.value = new LongInteger( s );
                this.exact = true;
//            } else {
//                this.value = new Double( s );
//                this.exact = false;
            }
        } catch( Exception exception ) {
            throw new RuntimeException( s + " is not a number" );
        }

//        System.out.println( "Created NumberToken " + this.toString() );

    }


    /**Constructor from a number
     * Determines the appropriate type from the number.
     *
     * @param num   Number to store in token.
     * @throws ParserException
     */
    public Value( LongInteger num ) throws ParserException {
        this.value = num;

//        System.out.println( "Creating NumberToken from number " + num.toString() );

        if( num instanceof LongInteger )
            this.exact = true;
//        else if( num instanceof Double )
//            this.exact = false;
        else
            throw new ParserException( num + " is not a known number type" );

//        System.out.println( "Created NumberToken " + this.toString() );

    }

	public LongInteger value() { return this.value; }

    @Override
    public String toString()  { return this.value.toString(); }

	public boolean equals( Expression exp ) {
        if( exp instanceof Value )
            return this.value.equals( ((Value) exp).value );
        else
            return false;
    }

//	@Override
//	public Value pow( Expression exp ) throws ParserException {
////		if( exp instanceof Value ) {
////			Value v = (Value) exp;
////			return new Value( this.value.pow( v.value.intValue() ));
////		}
////		else
////			throw new ParserException("Power function requires an integer exponent");
//		throw new ParserException("Value power function not yet supported");
//	}

	@Override
	public Expression add(Expression exp) {
		if( this.value.equals( LongInteger.ZERO ) )
			return exp;
		else if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.add( num.value ));
		} else if( exp instanceof Polynomial ) {
			Polynomial poly = new Polynomial(this, new Word("foo"));
//			System.out.println( "Created polynomial " + poly.toString() );
			return poly.add( exp );
		} else
			return super.add( exp );
	}

	@Override
	public Expression subtract(Expression exp) {
		if( this.value.equals( LongInteger.ZERO ) )
			return exp.negate();
		else if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.subtract( num.value ));
		} else if( exp instanceof Polynomial ) {
			Polynomial poly = new Polynomial(this, new Word("foo"));
//			System.out.println( "Created polynomial " + poly.toString() );
			return poly.subtract( exp );
		} else
			return super.subtract( exp );
	}

	@Override
	public Expression multiply(Expression exp) {
		if( this.value.equals( LongInteger.ZERO ) )
			return this;
		else if( this.value.equals( LongInteger.ONE ) )
			return exp;
		else if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.multiply( num.value ));
		} else if( exp instanceof Polynomial ) {
			return exp.multiply( this );
		} else
			return super.multiply( exp );
	}

	@Override
	public Expression divide(Expression exp) {
		if( this.value.equals( LongInteger.ZERO ) )
			return this;
		else if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.divide( num.value ));
		} else
			return super.divide( exp );
	}

	@Override
	public Expression remainder(Expression exp) {
		if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.remainder( num.value ));
		} else
			return super.remainder( exp );
	}

	@Override
	public Expression gcd(Expression exp) {
		if( exp instanceof Value ) {
			Value num = (Value) exp;
			return new Value( this.value.gcd( num.value ));
		} else
			return super.gcd( exp );
	}

	@Override
	public Expression negate() {
		return new Value( this.value.negate());
	}

	@Override
	public Expression replace( Word word, Expression exp ) { return this; }

	@Override
	public boolean contains( Word word ) { return false; }

	/** Evaluate the expression in the given context.
	 * This does not change the original expression.
	 *
	 * @param context Context in which to evaluate the expression.
	 * @return Evaluated expression.
	 */
	@Override
	public Expression evaluate( Context context ) {
		return this;
	}

}

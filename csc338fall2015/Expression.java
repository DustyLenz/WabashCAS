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
public class Expression implements Token {

	Operator operator;		// Use a separate type?
	Expression operand1;
	Expression operand2;

	public Expression() {
		this.operator = null;
		this.operand1 = null;
		this.operand2 = null;
	}

	public Expression( Expression exp ) {
		this.operator = exp.operator;
		this.operand1 = exp.operand1;
		this.operand2 = exp.operand2;
	}

	public Expression( Operator op, Expression op1, Expression op2 ) {
		this.operator = op;
		this.operand1 = op1;
		this.operand2 = op2;
	}

	public Expression( Operator.Type type, Expression op1, Expression op2 ) {
		this.operator = new Operator( type );
		this.operand1 = op1;
		this.operand2 = op2;
	}

	@Override
	public String toString() {
		String result = "";
		if( this.operator != null ) {
//			result += this.operator.string() + "(" + this.operand1.toString();
//			if( this.operator.operands() > 1 )
//				result += "," + this.operand2.toString();
//			result += ")";

			if( this.operator.operands() == 1 ) {
				result = this.operator.string();
				if( this.operand1.operator != null
						&& this.operand1.operator.precedence() > this.operator.precedence() )
					result += "(" + this.operand1.toString() + ")";
				else
					result += this.operand1.toString();
			}
			else {	// Binary operator
				if( this.operand1.operator != null
						&& this.operand1.operator.precedence() < this.operator.precedence() )
					result = "(" + this.operand1.toString() + ")";
				else
					result = this.operand1.toString();

				result += this.operator.string();

				if( this.operator.equals( Operator.Type.SUBTRACT )
					|| this.operator.equals( Operator.Type.DIVIDE )
					|| ( this.operand2.operator != null
						&& this.operand2.operator.precedence() < this.operator.precedence() )
					)
					result += "(" + this.operand2.toString() + ")";
				else
					result += this.operand2.toString();
			}
			// TODO: Ignore ^1? (EXPONENT 1)

		}
		return result;
	}

	public Expression add( Expression exp ) {
		if( exp instanceof Value ) {
			Value value = (Value) exp;

			if( LongInteger.ZERO.equals( value.value() ) )
				return this;
		}

		return new Expression( Operator.Type.ADD, this, exp );
	}

	public Expression subtract( Expression exp ) {
		if( exp instanceof Value ) {
			Value value = (Value) exp;

			if( LongInteger.ZERO.equals( value.value() ) )
				return this;
		}

		return new Expression( Operator.Type.SUBTRACT, this, exp );
	}

	public Expression multiply( Expression exp ) {
		if( exp instanceof Value ) {
			Value value = (Value) exp;

			if( LongInteger.ZERO.equals( value.value() ) )
				return exp;
			else if( LongInteger.ONE.equals( value.value() ) )
				return this;
		}

		return new Expression( Operator.Type.MULTIPLY, this, exp );
	}

	public Expression divide( Expression exp ) {
		if( exp instanceof Value ) {
			Value value = (Value) exp;

			if( LongInteger.ONE.equals( value.value() ) )
				return this;
		}

		return new Expression( Operator.Type.DIVIDE, this, exp );
	}

	public Expression remainder( Expression exp ) {
		return new Expression( Operator.Type.REMAINDER, this, exp );
	}

	public Expression negate( ) {
		return new Expression( Operator.Type.UNARY_MINUS, this, null );
	}

	public Expression gcd( Expression exp ) {
		return new Expression( Operator.Type.GCD, this, exp );
	}

	public Expression pow( Expression exp ) throws ParserException {
//		if( exp instanceof Value ) {
//			Value value = (Value) exp;
//
//			if( LongInteger.ZERO.equals( value.value() ) )
//				return new Value( LongInteger.ONE );
//			else if( LongInteger.ONE.equals( value.value() ) )
//				return this;
//			else
//				return new Expression( Operator.Type.EXPONENT, this, exp );
//		}

		return new Expression( Operator.Type.EXPONENT, this, exp );
	}

//	public Expression gcd( Expression exp );

	@Override
	public boolean equals(Token t) {
		if( !(t instanceof Expression) )
			return false;
		else
			return this.equals( (Expression) t );

//		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String string() {
		return this.toString();
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Expression replace( Word word, Expression exp ) {

//		System.out.println( "Called replace on " + this.toString() );

		Expression op1 = null;
		Expression op2 = null;

		if( this.operand1 != null )
			op1 = this.operand1.replace(word, exp);

		if( this.operand2 != null )
			op2 = this.operand2.replace(word, exp);

		return new Expression( this.operator, op1, op2 );
	}

	public boolean contains( Word word ) {

//		System.out.println( "Called contains on " + this.toString() );

		boolean contained = false;

		if( this.operand1 != null )
			contained = this.operand1.contains(word);

		if( contained )
			return true;
		else if( this.operand2 != null )
			return this.operand2.contains(word);

		return false;
	}

	public Expression apply( Operator op, Expression exp ) {

		// TODO: Apply also in a context?  Include inc/dec operators

//		System.out.printf( "Called apply on %s, %s\n", this.toString(), op.toString() );

		Expression result = this;

		// Recombine based on operator type
		switch( op.optype() ) {
				case ADD:       result = result.add(exp);           break;
				case SUBTRACT:  result = result.subtract(exp);           break;
				case MULTIPLY:  result = result.multiply(exp);           break;
				case DIVIDE:    result = result.divide(exp);           break;
				case REMAINDER: result = result.remainder(exp);           break;
				case EXPONENT:	result = result.pow(exp);	break;
                case UNARY_MINUS:   result = result.negate();     break;
                case GCD:		result = result.gcd(exp);     break;
				default:
					System.out.printf("Not yet implemented operator %s\n", 	op.toString() );
					throw new KernelException(" Not yet implemented operator " + operator.toString() );
		}

		return result;
	}

	/** Evaluate the expression in the given context.
	 * This does not change the original expression.
	 *
	 * @param context Context in which to evaluate the expression.
	 * @return Evaluated expression.
	 * @throws KernelException
	 */
	public Expression evaluate( Context context ) throws KernelException {

//		System.out.println( "Called evaluate on " + this.toString() );

		Expression op1 = null;
		Expression op2 = null;

		// Recursively evaluate operands
		if( this.operand1 != null )
			op1 = this.operand1.evaluate( context );

		if( this.operand2 != null )
			op2 = this.operand2.evaluate( context );

		// Apply the operator
		return op1.apply(operator, op2);
	}
}

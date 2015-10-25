/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

/**
 *
 * @author turnerw
 */
public class Word extends Expression {

    String word;

    /** Copy constructor
     *
     * @param var NumberToken to copy
     */
    public Word( Word var ) {
        this.word = var.word;
    }

    /** Constructor from a string
     * Determines the appropriate type from the string.
     *
     * @param s String representing the token
     */
    public Word( String s ) throws ParserException {
        this.word = s;

//        System.out.println( "Creating WordToken from string " + s );
    }

    /** Test if token equals this.
     * Equal if wordss are equal.
     *
     * @param exp Token to check for equality
     * @return Whether equal
     */
    public boolean equals( Expression exp ) {
        if( exp instanceof Word )
            return this.word.equals( ((Word) exp).word );
        else
            return false;
    }

    @Override
    public String toString() {
        return this.word;
    }

	@Override
	public Expression replace( Word word, Expression exp ) {

//		System.out.println( "Called replace on " + this.toString() );

		if( this.word.equals(word.word) ) {
			System.out.println("Replacing word!");
			return exp;
		}
		else
			return this;
	}

	@Override
	public boolean contains( Word word ) {

//		System.out.println( "Called contains on " + this.toString() );

		if( this.word.equals( word.word ) )
			return true;
		else
			return false;
	}

	/** Evaluate the expression in the given context.
	 * This does not change the original expression.
	 *
	 * @param context Context in which to evaluate the expression.
	 * @return Evaluated expression.
	 */
	@Override
	public Expression evaluate( Context context ) {
//		System.out.println( "Called evaluate on " + this.toString() );
		return context.evaluate(this);
	}

}

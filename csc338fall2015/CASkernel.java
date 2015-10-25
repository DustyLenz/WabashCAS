/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

//import java.math.LongInteger;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/** Kernel of computer algebra system developed for CSC/MAT 338 Fall 2011.
 *
 * @author turnerw
 */
public class CASkernel {

    // Members to keep track of inputs and outputs from the kernel
    Stack< String > inputs;
    Stack< String > outputs;
	Context context;


    /** Default constructor.
     */
    public CASkernel() {
        // Initialize members
        this.inputs = new Stack< String >();
        this.outputs = new Stack< String >();
		this.context = new Context();

		// Initialize moduli
		this.run( "modulus = 0" );
		this.run( "polynomialModulus = 0" );

    }

    /** How many commands have been getInput?
     *
     * @return Count of the commands getInput.
     */
    public int inputCount() {
        return this.inputs.size();
    }

    /** How many commands have been processed?
     * (This should always be the same as the number getInput.)
     *
     * @return Count of the commands processed.
     */
    public int outputCount() {
        return this.outputs.size();
    }

    /** Retrieve last command getInput.
     *
     * @return  String representing last command getInput.
     * @throws KernelException
     */
    public String lastInput() throws KernelException {
        return this.getInput( this.inputCount() );
    }

    /** Retrieve a specific getInput.
     *
     * @param i Index of getInput to retrieve.
     * @return  String representing the ith getInput.
     * @throws KernelException
     */
    public String getInput( int i ) throws KernelException {

        try {
            return inputs.get( i-1 );
        } catch (Exception exception) {
            throw new KernelException("Unable to retrieve input " + i);
        }
    }

    /** Retrieve last getOutput.
     *
     * @return  String representing last getOutput.
     * @throws KernelException
     */
    public String lastOutput() throws KernelException {
        return this.getOutput( this.outputCount() );
    }

    /** Retrieve a specific getOutput.
     *
     * @param i Index of getOutput to retrieve.
     * @return  String representing the ith getOutput.
     * @throws KernelException
     */
    public String getOutput( int i ) throws KernelException {

        try {
            return outputs.get( i-1 );
        } catch (Exception exception) {
            throw new KernelException("Unable to retrieve output " + i);
        }
    }

    /** Execute a command.
     *
     * @param input Command to execute
     * @return Output from command
     */
    public String run( String input ) {

        // Call lexical analyzer to tokenize the getInput.
        List< Token > tokens;
        tokens = this.tokenize( input );

//        System.out.println( tokens.toString() );

        // If not tokens, no statements
        if( tokens.isEmpty() )
            return "";

        // Know there is at least one result
        Token token = this.execute( tokens );

        // Store both input and output
        this.inputs.add(input);
        String output = token.string();
        this.outputs.add(output);

        // Return rhs of the command.
        return output; // token.toString();
    }

    /** Lexical analyzer.
     * Turns an getInput string into a list of tokens.
     *
     * This makes some assumptions about operators (e.g., +, -, ++, --) that
     * will need to be checked and possibly changed in the parser based on
     * other tokens around them.  (See Operator class for the assumptions.)
     *
     * @param str   Input to tokenize.
     * @return      List of tokens representing the getInput.
     */
    private List< Token > tokenize( String str ) {

        // Put the getInput int a mutable form for the
        StringBuilder input = new StringBuilder( str );
//        System.out.println("Input to tokenize is " + input.toString() );

        // Create list for getOutput
        LinkedList< Token > tokens = new LinkedList< Token >();
        char chr;

        while( input.length() > 0 ) {

//            System.out.printf("Before changed, input = '%s'\n", input.toString() );

            chr = input.charAt( 0 );
            int length = 0;     // Number of characters to consider
            if( Character.isWhitespace( chr ) ) {
                // Find length of whitespace
                while( input.length() > 0 && Character.isWhitespace( input.charAt( length ) ) )
                    ++length;
            } else if( Character.isDigit( chr )  || chr == '.' ) {
                // Number token

                // Find end of LongInteger part
                while( length < input.length() && Character.isDigit( input.charAt( length ) ) )
                    ++length;

                // Is it an LongInteger or a float?
                if( length < input.length() && input.charAt( length ) == '.' ) {
                    ++length;

                    while( length < input.length() && Character.isDigit( input.charAt( length ) ) )
                        ++length;
                }

                // Create new Value from the string
                tokens.add( new Value( input.substring( 0, length ) ) );
//                System.out.println( "Created Value " + tokens.getLast().toString() );

            } else if( Character.isLetter( chr ) ) {
                // Word token

                while( length < input.length() && Character.isLetterOrDigit( input.charAt( length ) ) )
                    ++length;

                // Create new Word from the string
                tokens.add( new Word( input.substring( 0, length ) ) );
//                System.out.println( "Created Word " + tokens.getLast().toString() );

            } else {    // Otherwise an operator
                length = 1;  // Know has length at least 1
                Operator optoken = null;

                // If a next character, check it to see if could be part of the operator
                if( input.length() > 1
                        && !Character.isLetterOrDigit(chr = input.charAt(1))
                        && chr != '.' ) {

                    try{
                        optoken = new Operator( input.substring(0, 2) );
                        length = 2;
                    } catch (ParserException exception) {
//                        System.out.println( input.substring(0, 2) + " is not an operator");
                    }
                }

                // If successful so far, try three!
                if( optoken != null && input.length() > 2
                        && !Character.isLetterOrDigit(chr = input.charAt(2))
                        && chr != '.' ) {

                    try {
                        optoken = new Operator( input.substring(0, 3) );
                        length = 3;
                    } catch (ParserException exception) {
//                        System.out.println( input.substring(0, 3) + " is not an operator");
                    }
                }

                // If not successful above, try one character.  Crash if not successful
                if( optoken == null) {
                    optoken = new Operator( input.substring(0, 1) );
                    length = 1;
                }

//                tokens.add( new Operator( input.substring( 0, length ) ) );
                tokens.add( optoken );
//                System.out.println( "Created OperatorToken " + tokens.getLast().toString() );

            }

            // Delete string used from input
            input.delete( 0, length );

//            System.out.printf("After changed, input = '%s'\n", input.toString() );

        }

        return tokens;
    }

    /** Convert list of tokens to string suitable for error messages.
     *
     * @param statement
     * @return
     */
    private String toString( List< Token > statement ) {
        ListIterator< Token > itr = statement.listIterator();
        StringBuilder str = new StringBuilder();

        while( itr.hasNext() ) {
            str.append( itr.next().string() );
        }

        return str.toString();
    }

    /** Execute a series of commands in a list of tokens.
     *
     * @param input Commands to execute
     * @return Token representing ouput
     */
    private Expression execute ( List< Token > input ) {

//        System.out.println( "Executing " + input.toString() );
//        System.out.println( "Executing " + this.toString(input) );

        Expression exp = null;       // Should never need to return this.

        while( !input.isEmpty() ) {

            // Find end of first statement
            int eos = this.findEndOfStatement(input);
//            System.out.println( "First statement ends after token " + eos );
//             System.out.println( "First statement: " + input.subList(0, eos).toString() );

            // Execute the command.
            // If eos == -1, execute entire list and return result
            if( eos == -1 ) {
                exp = this.executeCommand( input ); // Also removes command
            } else {  // Statement ended with EOS result; strip it
                exp = this.executeCommand( input.subList(0, eos-1)); // Removes command
                // result = null;
                input.remove(0);    // Remove EOS result
            }

//            System.out.println( "After removing command: " + input.toString() );
        }

        return exp;

    }

    /** Find end of first statement result in the list of tokens
     * Returns the index just past the end of the statement result.
     * Returns -1 if no EOS result in the list.
     *
     * @param input Tokens of command to analyze
     * @return index past end of first statement
     * @throws ParserException
     */
    private int findEndOfStatement ( List< Token > input ) throws ParserException {

//        System.out.println( "Finding end of statement in " + input.toString() );

        int openParen = 0;      // How many open parentheses there are
        Token token;
        ListIterator< Token > itr;
        Operator optoken;

        itr = input.listIterator();

        // Search for EOS tokens
        while( itr.hasNext() ) {
            token = itr.next();

            if( token instanceof Operator ) {
                optoken = (Operator) token;

                // Keep track of open parentheses as we go
                if( optoken.isLeftParenthesis() )
                    ++openParen;
                else if( optoken.isRightParenthesis() ) {
                    --openParen;

                    // Negative openParen means more right parentheses than left
                    if( openParen < 0 )
                        throw new ParserException("Right parenthesis without matching left parenthesis.");

                } else if( optoken.isEndOfStatement() && openParen == 0 )
                    // Only return if parentheses match
                    return itr.nextIndex();
            }

            // Skip non-operator tokens

        }

        // If here, must have reached the end of the list and never found EOS
        // Check for open parentheses before returning
        if( openParen > 0 )
            throw new ParserException("Never close parenthesis.");

        return -1;

    }

    /** Execute a single command and remove its list
     * It is either an assignment statement (include inplace arithmetic) or it
     * evaluates to a value.
     *
     * It will not end with an end-of-statement result.
     *
     * @param input Command to execute
     * @return Token representing output of command
     * @throws KernelException
     */
    private Expression executeCommand( List< Token > input ) throws KernelException {

//        System.out.println( "Executing command " + this.toString(input) );
		
		// Set moduli 
		Expression modulus = context.evaluate( "modulus" );
		if( modulus instanceof Value )
			LongInteger.modulus( ((Value) modulus).value() );
		
		modulus = context.evaluate( "polynomialModulus" );
		if( modulus instanceof Polynomial )
			LongIntegerPolynomial.modulus( ((Polynomial) modulus).value() );
//		else if( modulus instanceof Value )
//			LongIntegerPolynomial.modulus( LongIntegerPolynomial.valueOf(((Value) modulus).value() ) );
		

		// On with the command!
        Expression result = null;
        int test;       // Can be used in different roles in checking which type of statement

        // If only one word, look it up
        if( input.size() == 1 ) {

			Token token = input.get(0);

            if ( token instanceof Word ) {
                result = this.context.evaluate( (Word) token );
            } else if ( token instanceof Expression ) {
				result = (Expression) token;
                // Want to return result, but must wait until end to clean up the list.
            } else { // Operator alone is an invalid statement.
                throw new ParserException("Do not understand command " + this.toString(input) );
            }
//        } else if( input.size() == 2 ) {
//            TODO: Implement unary operators
        } else if( (test = this.findAssignment( input )) != -1 ) {
            /* Is it an assignment statement?
             * Here, test marks the index of the first assignment operator.
             *
             * Evaluate all assignments here because their associativity is
             * right to left (not left to right) and also so that we do not
             * need a keyword for polynomial assignment.
             */
            result = this.evaluateAssignment( input, test );
//        } else if() {
            // TODO: Conditional operator???
        } else {
            /* Find parentheses (outer to inner, left to right?)
             * and call this.execute on them to replace them with their
             * evaluated values.
             */
            int leftParen, rightParen;

            // Iterate as long as there is a left parenthesis
            while( (leftParen = this.findLeftParenthesis(input) ) != -1 ) {
                rightParen = this.findRightParenthesis(input);

                if( rightParen == -1 )
                    throw new ParserException("Unclosed parentheses pair");

                // Insert phantom multiplication after right parenthesis 
				// now before changing its index
                if( rightParen < input.size() - 1
                        && !(input.get(rightParen+1) instanceof Operator) )
                    input.add(rightParen+1, new Operator(Operator.Type.MULTIPLY) );

                /* TODO: Implement function calls, including keywords like GCD.
                 * If function name, evaluate inside then it.
                 * Decrement leftParen so it points to function name and
                 * replace entire sublist(leftParen,rightParen) with
                 * its evaluation (in helper function?).
                 * Otherwise, do as below and execute expression and replace it
                 * and its parentheses with result.
                 * Do this before inserting phantom left multiplication.
                 */

//				Token token;
//
//				if( leftParen > 0 && (token = input.get(leftParen-1)) instanceof Word ) {
//
//					Word word = (Word) token;
////					System.out.println("word = " + word.toString() );
//
//					if( word.toString().equals("gcd") )
//						System.out.println("Want to compute gcd" + input.subList(leftParen, rightParen+1));
//					else if( this.context.contains( word ) && this.context.evaluate(word) instanceof Function )
////						System.out.println("Want to evaluate " + word.toString() );
//						result = this.executeFunction(input.subList(leftParen-1, rightParen+1));
//					else // Insert phantom multiplication and iterate again
//						input.add(leftParen, new Operator(Operator.Type.MULTIPLY) );
//
//				} else if( leftParen > 0 && input.get(leftParen-1) instanceof Function ) {

//				// Preprocess to insert phantom multiplication if necessary or
//				// find function
//				if( leftParen > 0 ) {
//
//					Token token = input.get(leftParen-1);
//
//					// Function?
//					if( token instanceof Word ) {
//
//						Word word = (Word) token;
//	//					System.out.println("word = " + word.toString() );
//
//						if( word.toString().equals("gcd") ) {
//							result = this.gcd(input.subList(leftParen-1, rightParen+1));
//							System.out.println( "after gcd, input = " + input );
////							throw new UnsupportedOperationException( "GCD not yet supported" );
//						} else if( this.context.contains( word )
//								&& this.context.evaluate(word) instanceof Function ) {
//	//						System.out.println("Want to evaluate " + word.toString() );
//
//							// Replace word token with the function
//							input.add( leftParen-1, this.context.evaluate(word));
//							input.remove( leftParen );
//						}
//					}
//
//				}
//
//				// If function, execute
//				if( leftParen > 0 && input.get(leftParen-1) instanceof Function ) {
//					result = this.executeFunction(input.subList(leftParen-1, rightParen+1));
//				} else {	// Parentheses only for grouping
//
////					System.out.println("Before parentheses: " + this.toString(input));
//
//					result = this.execute( input.subList(leftParen+1, rightParen) );
//					input.remove( leftParen ); // Remove left parenthesis
//					input.remove( leftParen ); // Remove right parenthesis
//					input.add(leftParen, result); // Insert result
//
////					System.out.println("After parentheses: " + this.toString(input));
//
//					// Insert phantom multiplication to left after everything else.
//					if( leftParen > 0
//							&& !(input.get(leftParen-1) instanceof Operator) )
//						input.add(leftParen, new Operator(Operator.Type.MULTIPLY) );
//
//				} // Grouping

				// Are parentheses for function or just grouping?
				if( leftParen == 0 ) { // Nothing to left; must be grouping
					result = this.execute( input.subList(leftParen+1, rightParen) );
					input.remove( leftParen ); // Remove left parenthesis
					input.remove( leftParen ); // Remove right parenthesis
					input.add(leftParen, result); // Insert result
				} else { // Something to left; function?

					Token token = input.get(leftParen-1);
					if( token instanceof Word ) { // Word might be a function

						Word word = (Word) token;
	//					System.out.println("word = " + word.toString() );

						if( word.toString().equals("gcd") ) {	// GCD
							result = this.gcd(input.subList(leftParen-1, rightParen+1));
							System.out.println( "after gcd, input = " + input );
//							throw new UnsupportedOperationException( "GCD not yet supported" );
						} else if( this.context.contains( word )
								&& this.context.evaluate(word) instanceof Function ) {
							// Function!
//							System.out.println("Want to evaluate " + word.toString() );
							result = this.executeFunction(input.subList(leftParen-1, rightParen+1));
						} else { // Must be phantom multiplication
							result = this.execute( input.subList(leftParen+1, rightParen) );
							input.remove( leftParen ); // Remove left parenthesis
							input.remove( leftParen ); // Remove right parenthesis
							input.add(leftParen, result); // Insert result

							// Insert phantom multiplication to left after everything else.
							if( leftParen > 0
									&& !(input.get(leftParen-1) instanceof Operator) )
								input.add(leftParen, new Operator(Operator.Type.MULTIPLY) );
						}
					} else if( token instanceof Function ) {
						result = this.executeFunction(input.subList(leftParen-1, rightParen+1));
					} else { // Must be phantom multiplication
						result = this.execute( input.subList(leftParen+1, rightParen) );
						input.remove( leftParen ); // Remove left parenthesis
						input.remove( leftParen ); // Remove right parenthesis
						input.add(leftParen, result); // Insert result

						// Insert phantom multiplication to left after everything else.
						if( leftParen > 0
								&& !(input.get(leftParen-1) instanceof Operator) )
							input.add(leftParen, new Operator(Operator.Type.MULTIPLY) );
					}

				}

//				System.out.println("** Tokens now " + this.toString(input) );
//				System.out.println("** Input size now " + input.size() );

            } // While parentheses

            // When all parentheses gone, evaluate remaining expression if any
			if( !input.isEmpty() )
				result = this.evaluatePostFix( this.infix2postfix(input) );
        }

//        System.out.println( "Output of command is " + result.toString() );

        input.clear();      // Clear from list

        return result;
    }

	private Expression executeFunction( List< Token > input ) throws KernelException {

//        System.out.println( "Executing function " + this.toString(input) );

		// Ensure proper form
		Token name = input.get(0);
		if( input.size() < 4
				|| !( name instanceof Word || name instanceof Function )
                || !(input.get(1) instanceof Operator)
                || !(input.get( input.size() - 1 ) instanceof Operator)
                || !((Operator) input.get(1)).isLeftParenthesis()
                || !((Operator) input.get( input.size() - 1) ).isRightParenthesis()
                ) {
            throw new ParserException("Not a function to evaluate: " + this.toString(input));
		}

		// Retrieve function
		Expression exp;
		if( name instanceof Word )
			exp = this.context.evaluate( (Word) name );
		else
			exp = (Expression) name;

		if( !(exp instanceof Function) )
			throw new KernelException( "Not a funciton: " + exp.toString() );

		// Apply the function
		Function fun = (Function) exp;
		Expression param = this.execute( input.subList(2, input.size() - 1) );
		exp = fun.apply(param, this.context);

		// Clear and return
		input.clear();
		input.add( exp );
		return exp;

	}

    /** Find end of first assignment or inplace airthmetic result in the list of tokens
     * Returns the index of the assignment result.
     * Returns -1 if no assignment result in the list.
     *
     * @param input Tokens of command to analyze
     * @return index past end of first statement
     * @throws ParserException
     */
    private int findAssignment( List< Token > input ) {

//        System.out.println( "Finding assignment in " + input.toString() );

        int openParen = 0;      // How many open parentheses there are
        Token token;
        ListIterator< Token > itr;
        Operator optoken;

        itr = input.listIterator();

        // Search for assignment tokens
        while( itr.hasNext() ) {
            token = itr.next();

            if( token instanceof Operator ) {
                optoken = (Operator) token;

                // Keep track of open parentheses as we go
                if( optoken.isLeftParenthesis() )
                    ++openParen;
                else if( optoken.isRightParenthesis() ) {
                    --openParen;

                    // Negative openParen means more right parentheses than left
                    if( openParen < 0 )
                        throw new ParserException("Right parenthesis without matching left parenthesis.");

                } else if( optoken.isAssignment() && openParen == 0 )
                    // Only return if parentheses match, otherwise this is an
                    // enclosed (?) assignment statement
                    return itr.nextIndex()-1;
            }

            // Skip non-operator tokens

        }

        // If here, must have reached the end of the list.
        // Check for open parentheses before returning
        if( openParen > 0 )
            throw new ParserException("Never close parenthesis.");

        return -1;  // Not an assignment statement

    }

    /** Execute an assignment or inplace arithmetic operation.
     *
     * Because of their low precedence and right to left associativity,
     * everything to the left of the assignment operator must be the LHS.
     *
     * This evaluates the expression but does NOT clear the list.
     *
     * @param input List of tokens representing the command
     * @param assign    Position of assignment operator in the list
     * @return      Token representing the output (the RHS)
     * @throws ParserException
     */
    private Expression evaluateAssignment( List< Token > input, int assign ) throws ParserException {
        /* Because of their low precedence and right to left associativity,
         * everything to the left of the assignment operator must be the LHS.
         */

//        System.out.printf( "Evaluating assignment %s\n    LHS = %s\n    RHS = %s\n",
//                input.toString(), this.toString(input.subList(0,assign)),
//                this.toString(input.subList(assign+1,input.size())) );

        // First token must be name to store
        Token name = input.get(0);
        if( !(name instanceof Word) )
            throw new ParserException( this.toString(input.subList(0, assign))
                    + " is not a valid left hand side for an assignment.");

        // If inplace arithmetic, check if already stored and then convert to
        // explicit assignment and arithmetic changing RHS
        Operator assignToken;
        try {
            assignToken = (Operator) input.get(assign);
        } catch (Exception exception) {
            throw new KernelException("*** Not an assignment operator: " + exception.toString() );
        }

        if( !assignToken.equals( Operator.Type.ASSIGN ) ) {
            // Must be inplace arithmetic
            // Word must be able to be evaluated (already stored)
            if( !this.context.contains( name.string() ) )
                throw new KernelException( name.string() + " does not have a value already" );

            // Convert to explicit assignment and arithmetic changing RHS
//            System.out.printf("*** Arithmetic operator '%s'\n", assignToken.string().replaceAll("=", "") );
            Operator arithToken = new Operator( assignToken.string().replaceAll("=","") );
            assignToken = new Operator( Operator.Type.ADD );
            input.remove(assign);
            input.add(assign, arithToken);
            input.addAll(assign, input.subList(0, assign));
            input.add( assign, assignToken );
//            System.out.printf("** New input = '%s'\n", input);
        }

        // Assigning a number or a polynomial?
        if( assign == 1 ) { // Must be a number

            Expression rhs = this.execute( input.subList(assign+1, input.size() ) );

			// Ensure not recursive definition
			if( rhs.contains( (Word) name ) )
				throw new KernelException( "Recursive definition of " + name.string() );

//            System.out.println( "lhs = " + lhs.string() + ", rhs = " + rhs.string() );
            this.context.define(name.string(), rhs);

            return rhs;

        }

        // Must be a polynomial, but check for correct form f(x) =

        if( assign != 4
                || !(input.get(0) instanceof Word)
                || !(input.get(2) instanceof Word)
                || !(input.get(1) instanceof Operator)
                || !(input.get(3) instanceof Operator)
                || !((Operator) input.get(1)).isLeftParenthesis()
                || !((Operator) input.get(3)).isRightParenthesis()
                ) {
//            throw new ParserException("Invalid LHS: " + input.subList(0,assign).toString());
            throw new ParserException("Invalid LHS: " + this.toString(input.subList(0,assign)));
        }

		// TODO: Replace variable name with new obscure name in case already has a value ?

		// Evaluate the RHS to create an expression tree for easier parsing
        Expression rhs = this.execute( input.subList(assign+1, input.size() ) );
//		System.out.println("Poly RHS = " + rhs.toString() );

		// Ensure not recursive definition
		if( rhs.contains( (Word) name ) )
			throw new KernelException( "Recursive definition of " + name.string() );

		Word var = (Word) input.get(2);
		Polynomial poly = new Polynomial( rhs, var );

//		System.out.println( "Constructed polynomial " + poly.poly.coeffs.toString() );

        this.context.define(name.string(), poly);

		return poly.apply(var, this.context);
    }

    /** Find leftmost comma in list of tokens.
     *
     * @param input List of tokens representing command
     * @return Index of leftmost occurrence.  -1 if none.
     */
    private int findComma ( List< Token > input ) {

        Token token;
        Operator optoken;
        ListIterator< Token > itr = input.listIterator();

        // Search for assignment tokens
        while( itr.hasNext() ) {
            token = itr.next();

            if( token instanceof Operator ) {
                optoken = (Operator) token;
                if( optoken.isComma() )
                    return itr.nextIndex() - 1;
            }

            // Skip non-operator tokens
        }

        // If here, must have reached the end of the list.
        return -1;  // No comma
    }

    /** Find leftmost left parenthesis in list of tokens.
     *
     * @param input List of tokens representing command
     * @return Index of leftmost occurrence.  -1 if none.
     */
    private int findLeftParenthesis ( List< Token > input ) {

        Token token;
        Operator optoken;
        ListIterator< Token > itr = input.listIterator();

        // Search for assignment tokens
        while( itr.hasNext() ) {
            token = itr.next();

            if( token instanceof Operator ) {
                optoken = (Operator) token;

                if( optoken.isLeftParenthesis() )
                    return itr.nextIndex() - 1;
            }

            // Skip non-operator tokens
        }

        // If here, must have reached the end of the list.
        return -1;  // No left parentheses
    }

    /** Find right parenthesis of the left-most outer parentheses pair.
     *
     * @param input List of tokens representing command
     * @return Index of right parenthesis.  -1 if none.
     */
    private int findRightParenthesis ( List< Token > input ) {

        Token token;
        Operator optoken;
        ListIterator< Token > itr = input.listIterator();
        int openParen = 0;      // How many open parentheses there are

        // Search for assignment tokens
        while( itr.hasNext() ) {
            token = itr.next();

            if( token instanceof Operator ) {
                optoken = (Operator) token;

                // Keep track of open parentheses as we go
                if( optoken.isLeftParenthesis() )
                    ++openParen;
                else if( optoken.isRightParenthesis() ) {
                    --openParen;

                    // Zero openParen means end of outer parenthesis pair
                    if( openParen == 0 )
                        return itr.nextIndex() - 1;

                }
            }

            // Skip non-operator tokens

        }

        // If here, must have reached the end of the list.
        return -1;  // No right parentheses
    }

    /** Convert an infix statement into a postfix statement.
     * The infix statement contains no assignment or inplace arithmetic
     * operators, nor does it contain any parentheses.
     *
     * @param input An infix statement without any assignments or parentheses.
     * @return The equivalent postfix statement
     */
    private List< Token > infix2postfix( List< Token > input ) {

        // Storage for postfix queue.
        LinkedList< Token > postfix = new LinkedList< Token >();

        // Stack of operators for conversion
        Stack< Operator > operators = new Stack< Operator >();

        // Iterate over input list and convert tokens
        ListIterator< Token > itr = input.listIterator();
        Token token;
        Token prevToken = null; // for Operatorless multiplication and unary minus

        while( itr.hasNext() ) {
            token = itr.next();

//            System.out.println("Examining token " + token.toString());

            // Perform operation based on token type.
            // Do non-operators first in case need to insert phantom multiply.
            if( !(token instanceof Operator) ) {

                // Immediately put token on postfix queue
                postfix.add( token );

                // If input has another token, and that token is not an operator token,
                // insert phantom multiplication.
                if( itr.nextIndex() < input.size() ) {

                    Token nextToken = itr.next();   // Examine next token
                    itr.previous(); // Move back so insert in correct position

                    if( !(nextToken instanceof Operator) ) {
                        itr.add( new Operator(Operator.Type.MULTIPLY) );
                        itr.previous();     // Go back to the inserted one. (Why?)
//                        System.out.println("**** Tokens now " + this.toString(input) );
                    }
                }

            } else { // Is an operator

                Operator optoken = (Operator) token;
//                System.out.println( "*** Acting on token " + optoken.toString() );

		/* Operator may need to be converted if at beginning of
		 * expression or follows a binary operator
		 * (or follows left parentheses, but that would mean
		 * now at beginning of expression.
		 */
                if( prevToken == null
                        || ( prevToken instanceof Operator
                            && ((Operator) prevToken).operands() > 1 ) ) {

//                    System.out.println( "Convert operator!");

		    switch( optoken.optype() ) {
			    case ADD:
				optoken = new Operator( Operator.Type.UNARY_PLUS );
				break;
			    case SUBTRACT:
				optoken = new Operator( Operator.Type.UNARY_MINUS );
				break;
			    case UNARY_POST_INC:
				optoken = new Operator( Operator.Type.UNARY_PRE_INC );
				break;
			    case UNARY_POST_DEC:
				optoken = new Operator( Operator.Type.UNARY_PRE_DEC );
				break;
		    }
                }

                /* Perform operation based on the type of the token
                 * It cannot be parentheses, so don't check for them.
                 * See calculator for how to incorporate them if necessary.
                 */

                int prec = optoken.optype().precedence();

                // All operators of higher precedence go onto queue
                while( !operators.isEmpty()
                        && prec <= operators.peek().optype().precedence() )
                    postfix.add( operators.pop() );

                operators.push( optoken );

            }

//            System.out.println( "  ***  " );
//            System.out.println( "Infix queue: " + input.toString() );
//            System.out.println( "Postfix queue: " + postfix.toString() );
//            System.out.println( "Operator stack: " + operators.toString() );

            prevToken = token;  // Don't forget to update previous token!

        } // while( !this.tokens.isEmpty() )

        // Input queue empty, so empty operator queue onto postfix queue
        while( !operators.isEmpty() ) {
            postfix.add( operators.pop() );
//            System.out.println("After pop stack = " + operators.toString() );
        }

        // If stack is not empty, there must be more left parentheses.  Is this
        // a problem?  We can leave it alone if we want to allow it to continue
        // on the next line (runInFix string).  Otherwise, be need the following:
        if( !operators.isEmpty() )
            throw new ParserException( "More left parentheses than right. (*)" );

//        System.out.println( "  ***  " );
//        System.out.println( "Infix queue: " + this.tokens.toString() );
//        System.out.println( "Postfix queue: " + postfix.toString() );
//        System.out.println( "Operator stack: " + this.operators.toString() );

        // Return new statement
        return postfix;
    }

    /** Evaluate a statement in postfix notation.
     *
     * @param input Statement in postfix notation
     * @return Token representing result
     * @throws KernelException
     */
    private Expression evaluatePostFix( List< Token > input ) throws KernelException {

//        System.out.println( "Postfix: " + input.toString() );

        // Operands stack for calculation
        Stack< Expression > operands = new Stack< Expression >();

        // Iterate over input queue and calculate
        ListIterator< Token > itr = input.listIterator();
        Token token;
        Operator optoken;

        while( itr.hasNext() ) {
            token = itr.next();

            // Call appropriate helper method based on type of token
            if( token instanceof Operator ) {
                optoken = (Operator) token;
                if( optoken.operands() == 2 ) {
                    try {
                        operands.push( this.applyBinaryOperator( optoken, operands.pop(), operands.pop() ) );
                    } catch (EmptyStackException exception) {
                        throw new KernelException("Not enough operands for operator " + optoken.string() );
                    }
				} else if( optoken.operands() == 1 ) {
                    try {
                        operands.push( this.applyUnaryOperator( optoken, operands.pop() ) );
                    } catch (EmptyStackException exception) {
                        throw new KernelException("Not enough operands for operator " + optoken.string() );
                    }
				} else {
//                    System.out.printf( "Number of operands = %d?\n", optoken.operands() );
                    throw new KernelException("Invalid number of operands " + optoken.operands() );
                }
            }
//            else if( token instanceof Value )
//                operands.push( new Value( token.string() ) );
//            else if( token instanceof Word )
//                operands.push( new Word( token.string() ) );
            else if( token instanceof Expression )
                operands.push( (Expression) token );

//            System.out.println( "Operand stack: " + operands.toString() );
        }

        // Operand stack should only have one item on it.
        if( operands.size() != 1 ) {
            System.out.println( "Operands: " + operands.toString() );
            throw new KernelException("Invalid number of operands after postfix evaluation");
        }

        return operands.pop();

    }

	/** Apply an arithmetic operator to one operand.
     *
     * @param operator
     * @param op   Operand
     * @return      Token representing the result
     */
    private Expression applyUnaryOperator( Operator operator, Expression op ) {

//        System.out.printf( "Applying unary operator %s to operand %s\n",
//                operator.toString(), op.toString() );

		Operator.Type optype = operator.optype();

		// Call specialized increment/decrement method if appropriate
        switch( operator.optype() ) {
            case UNARY_POST_INC:
            case UNARY_POST_DEC:
            case UNARY_PRE_INC:
            case UNARY_PRE_DEC:
				return this.applyIncDec( operator, op );
		}

        // Evaluate names to numbers if possible
		op = op.evaluate(context);
		return op.apply(operator, null);
    }

    /** Apply an arithmetic operator to two operands.
     * Becareful!  The operands are passed second first to make it easier for
     * the postfix notation.  This will only be a problem if the operation
     * is not commutative, though.
     *
     * @param operator
     * @param op2   Second (right) operand
     * @param op1   First (left) operand
     * @return      Expression representing the result
     */
	private Expression applyBinaryOperator( Operator operator, Expression op2, Expression op1 ) {

//        System.out.printf( "Applying operator %s to operands %s and %s\n",
//                operator.toString(), op1.toString(), op2.toString() );

		op1 = op1.evaluate(context);
		op2 = op2.evaluate(context);

		return op1.apply(operator, op2);
    }

    /** Apply an increment or decrement operator.
	 * The operand must be a Expression representing a stored variable.
     *
     * @param operator
     * @param op   Operand
     * @return      Expression representing the result
	 * @throws KernelException
     */
    private Expression applyIncDec( Operator operator, Expression op ) throws KernelException {

//        System.out.printf( "Applying inc/dec operator %s to operand %s\n",
//                operator.toString(), op.toString() );

		Operator.Type optype = operator.optype();
		String name = null;

        // Evaluate names to numbers if possible
        if( op instanceof Word ) {
			name = op.toString();		// Store name before conversion
//            op = this.evaluateName( (Word) op );
            op = this.context.evaluate( (Word) op );
		}
		else {
			System.out.println("Must apply operator " + operator.string() + " to variable");
			throw new KernelException("Must apply operator " + operator.string() + " to variable");
		}

        // Now should be number.  If not, must make an expression to evaluate later.
        if( !(op instanceof Value) ) {
			System.out.println("Must apply operator " + operator.string() + " to variable already in memory");
			throw new KernelException("Must apply operator " + operator.string() + " to variable already in memory");
        }

        LongInteger a = (LongInteger) ((Value) op).value();
		LongInteger res = null;

        // Perform operation based on the operator type
        switch( operator.optype() ) {
            case UNARY_POST_INC:
				res = a;
				a = a.add(LongInteger.ONE);
				break;
            case UNARY_POST_DEC:
				res = a;
				a = a.subtract(LongInteger.ONE);
				break;
            case UNARY_PRE_INC:
				a = a.add(LongInteger.ONE);
				res = a;
				break;
            case UNARY_PRE_DEC:
				a = a.subtract(LongInteger.ONE);
				res = a;
				break;
        }

//		System.out.printf("Return value %d and store %s =  %d\n", res, name, a);
		this.context.define( name, new Value(a) );
        return new Value(res);

    }


	private Expression gcd( List< Token > input ) {
//		System.out.println( "Called GCD on " + input );

		// Ensure proper form
		Token name = input.get(0);
		if( input.size() < 4
				|| !( name instanceof Word )
				|| !((Word) name).word.equals("gcd")
                || !(input.get(1) instanceof Operator)
                || !(input.get( input.size() - 1 ) instanceof Operator)
                || !((Operator) input.get(1)).isLeftParenthesis()
                || !((Operator) input.get( input.size() - 1) ).isRightParenthesis()
                ) {
            throw new ParserException("Not a gcd to evaluate: " + this.toString(input));
		}

		// Find first comma.  If no comma, cannot compute GCD.
		int comma = this.findComma( input );
		if( comma == -1 )	// No comma
			throw new ParserException("GCD must have at least two arguments");
		
		// Retrieve first argument
		Expression exp1 = this.execute( input.subList(2, comma) );
//		System.out.println("exp1 = " + exp1 );
		
		// Loop over all remaining arguments
		boolean cont = true;
		while( cont ) {
			// comma now at index 2; remove it and continue
			input.remove( 2 );

			// Find next comma
			comma = this.findComma( input );
			if( comma == -1 ) {	// No more commas.  Last argument
				comma = input.size() - 1;
				cont = false;	// stop after this one
			}

			// telescoping GCD
			Expression exp2 = this.execute( input.subList(2, comma ) );
			exp1 = exp1.gcd(exp2);
		}		
		
		// Clear and return
		input.clear();
		input.add( exp1 );
		return exp1;
	}
}


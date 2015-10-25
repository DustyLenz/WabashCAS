/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc338fall2015;

/**
 *
 * @author turnerw
 */
public class Operator implements Token {

    Type optype;

    /** Copy constructor
     *
     * @param token Operator to copy
     */
    public Operator( Operator token ) {
        this.optype = token.optype;
    }

    /** Constructor from a string
     * Determines the appropriate type from the string.
     *
     * @param s String representing the token
     */
    public Operator( String s ) throws ParserException {

//        System.out.println( "Creating Operator from string " + s );

        for( Type type: Type.values() ) {

            // System.out.println( "Checking type " + type.name() );
            if( s.equals( type.string() ) ) {
                this.optype = type;
                break;      // Break the loop on first occurence
            }
        }

        if( this.optype == null )
            throw new ParserException("Unknown operator " + s);

//        System.out.println( "Created Operator " + this.optype.name() );

    }

    /** Constructor from a type.
     * The string is set from the type.
     *
     * @param optype Type of Operator to create
     */
    public Operator( Type optype ) {
        this.optype = optype;
    }

    /** Access the operator type of the token.
     *
     * @return Operator type of the token
     */
    public Type optype() { return this.optype; }

    /** Test if token equals this.
     * Equal if have same operator type.
     *
     * @param t Token to check for equality
     * @return Whether equal
     */
    public boolean equals( Token t ) {
        if( t instanceof Operator )
            return this.optype.equals( ((Operator) t).optype );
        else
            return false;
    }

    /** Test if token is of a particular type.
     * Equal if this has same operator type.
     *
     * @param t Type checked for equality
     * @return Whether equal
     */
    public boolean equals( Type t ) {
        return this.optype.equals( t );
    }

    public boolean isEndOfStatement() {
        return this.optype == Operator.Type.END_STATEMENT;
    }

    public boolean isComma() {
        return this.optype == Operator.Type.COMMA;
    }

    public boolean isLeftParenthesis() {
        return this.optype == Operator.Type.LEFT_PAREN;
    }

    public boolean isRightParenthesis() {
        return this.optype == Operator.Type.RIGHT_PAREN;
    }

    public boolean isAssignment() {
        switch (this.optype) {
            case ASSIGN:
            case ADD_ASSIGN:
            case SUBTRACT_ASSIGN:
            case MULTIPLY_ASSIGN:
            case DIVIDE_ASSIGN:
            case REMAINDER_ASSIGN:
                return true;
            default:
                return false;
        }
    }

    public int operands() { return this.optype.operands(); }

    public int precedence() { return this.optype.precedence(); }

    public String string() { return this.optype.string(); }

    @Override
    public String toString() {
        return "OPERATOR: " + this.optype.name();
    }

    protected enum Type {

        // Operators to group other tokens are given negative precedence
        // Does the precedence matter?

        END_STATEMENT   (";", -10, 0, 'N'), // End-of-Statement
        LEFT_PAREN      ("(", -3, 0, 'N'),  // Associative?
        RIGHT_PAREN     (")", -2, 0, 'N'),  // Associative?
        COMMA			(",", -1, 0, 'N'),  // Associative?

        // All of the following are in the order of Java

        ASSIGN      ("=", 0, 2, 'R'),       // Right to left associative
        ADD_ASSIGN  ("+=", 0, 2, 'R'),
        SUBTRACT_ASSIGN ("-=", 0, 2, 'R'),
        MULTIPLY_ASSIGN ("*=", 0, 2, 'R'),
        DIVIDE_ASSIGN   ("/=", 0, 2, 'R'),
        REMAINDER_ASSIGN    ("%=", 0, 2, 'R'),

        // TODO: Conditional operator ?: has precedence 1, but how to implement?

        COND_OR   ("||", 2, 2, 'L'),        // Left to right associative

        COND_AND   ("&&", 3, 2, 'L'),

        IS_EQUAL   ("==", 7, 2, 'L'),
        NOT_EQUAL   ("!=", 7, 2, 'L'),

        LESS_THAN       ("<", 8, 2, 'L'),
        LESS_EQUAL      ("<=", 8, 2, 'L'),
        GREATER_THAN    (">", 8, 2, 'L'),
        GREATER_EQUAL   (">=", 8, 2, 'L'),

        ADD         ("+", 10, 2, 'L'),
        SUBTRACT    ("-", 10, 2, 'L'),      // Before UNARY_MINUS so assume subtraction unless shown otherwise

        MULTIPLY    ("*", 11, 2, 'L'),
        DIVIDE      ("/", 11, 2, 'L'),
        REMAINDER   ("%", 11, 2, 'L'),

        EXPONENT    ("^", 12, 2, 'L'),

        UNARY_POST_INC ("++", 14, 1, 'R'),  // Default constructor to post increments
        UNARY_POST_DEC ("--", 14, 1, 'R'),

        UNARY_PRE_INC ("++", 13, 1, 'R'),
        UNARY_PRE_DEC ("--", 13, 1, 'R'),
        UNARY_PLUS ("+", 13, 1, 'R'),
        UNARY_MINUS ("~", 13, 1, 'R'),       // - or ~?
        UNARY_NOT ("!", 13, 1, 'R'),
		
		// Functions stored as operators
		
		GCD (":GCD:", -20, 2, 'L');	// Associativity?

        private final String string;
        private final int precedence;
        private final int operands;
        private final char associativity;

        Type( String string, int precedence, int operands, char assoc ) {
            this.string = string;
            this.precedence = precedence;
            this.operands = operands;
            this.associativity = assoc;
        }

        public String string() { return this.string; }
        public int precedence() { return this.precedence; }
        public int operands() { return this.operands; }
        public int associativity() { return this.associativity; }
    }

}
